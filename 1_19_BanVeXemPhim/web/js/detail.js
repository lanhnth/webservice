$(document).ready(function() {
    $(".fb-like").attr("data-href",window.location);
			var baseUrlImg = 'https://image.tmdb.org/t/p/w185_and_h278_bestv2';
			var baseUrl = 'https://api.themoviedb.org/3/movie/';
			var api_key = '931e54c126cfca6e31b0c905d36d6614';
			function createStrong(strong){
				return $(document.createElement('strong')).text(strong);
			}
			function createDiv(code,className) {
				return $(document.createElement(code)).attr('class',className);
			}
			function createDivAtrr(code, attrName, attrValue) {
				return $(document.createElement(code)).attr(attrName, attrValue);
			}
			function createDivInfo(labelx, className, text){
				var divName = $(document.createElement('div')).append("<label>"+labelx+"</label>");
				var divinfo = createDiv('div', className).text(text);
				divName.append(divinfo);
				return divName;
			}
			function createUrl(id) {
				return baseUrl+id+"?api_key="+api_key;
			}
			function createUrlVideo(id) {
				return baseUrl+id+"/videos?api_key="+api_key;
			}

			$(window).on('load', function() {
				
				fetch(createUrl(window.location.search.slice(window.location.search.lastIndexOf('=')+1)))
				.then((resp) => resp.json())
				.then( function(data){
					var strong_title = createStrong(data.title,);
					$('.product').append(data.title);
					var h2 = $(document.createElement('h2')).text(data.title);
					$('.moive-name').append(h2);
					// var daodien = createDivInfo('Đạo diễn : ', 'moive-info-content', 'dao dien abcdd');
					// var dienvien = createDivInfo('Diễn viên  : ', 'moive-info-content', 'dao dien abcdd');
					let all = data.genres;
					var genres="";
					$.each(all, function(key, value) {
						genres += ' ' + value.name + ',';
					});
					genres = genres.slice(0, genres.length -2);

					var production_countries = "";
					let countries = data.production_countries;
					$.each(countries, function(key, value) {
						production_countries += ' ' + value.name + ',';
					});
					production_countries = production_countries.slice(0, production_countries.length -2);

					var production_companies = "";
					let companies = data.production_companies;
					$.each(companies, function(key, value) {
						production_companies += ' ' + value.name + ',';
					});
					production_companies = production_companies.slice(0, production_companies.length -2);

					var theloai = createDivInfo('Genres : ', 'moive-info-content', genres);
					var nuocsx = createDivInfo('Production Countries : ', 'moive-info-content', production_countries);
					var ctysx = createDivInfo('Production Companies : ', 'moive-info-content', production_companies);
					var khoichieu = createDivInfo('Release Date : ', 'moive-info-content', data.release_date);
					var thoiluong = createDivInfo('Runtime :  ', 'moive-info-content', data.runtime);
					var rated = createDivInfo('Vote Average :  ', 'moive-info-content', data.vote_average);
					
					$('.moive-info').append(theloai);
					$('.moive-info').append(nuocsx);
					$('.moive-info').append(ctysx);
					$('.moive-info').append(khoichieu);
					$('.moive-info').append(thoiluong);
					$('.moive-info').append(rated);

					$('#home').text(data.overview);
					var img = createDivAtrr('img','src', baseUrlImg + data.backdrop_path);
					$('.moive-image').append(img);
				})
				.catch(function(error) {
					var err = $(document).createElement('h2').text(error.status_message);
				    $('.moive-info').append(err);
				}); 
				fetch(createUrlVideo(window.location.search.slice(window.location.search.lastIndexOf('=')+1)))
				.then((resp) => resp.json())
				.then( function(data){
					urlvd = data.results;
					var urlVideo = 'https://www.youtube.com/embed/'+ urlvd[0].key;
					//console.log(data.key);
					//var ifra = createDivAtrr('iframe', src = urlVideo)
					$('iframe').attr({
						src: urlVideo
					});
				})
				.catch(function(error) {
					var err = $(document).createElement('h2').text(error.status_message);
				    
				});
			});
			
		});