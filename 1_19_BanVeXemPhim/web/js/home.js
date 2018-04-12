$(document).ready(function() {
			var baseUrlImg = 'https://image.tmdb.org/t/p/w185_and_h278_bestv2';
			var baseUrl = 'https://api.themoviedb.org/3/movie/';
			var api_key = '931e54c126cfca6e31b0c905d36d6614';
			
			function createDiv(code,className) {
				return $(document.createElement(code)).attr('class',className);
			}
			function createDivAtrr(code, attrName, attrValue) {
				return $(document.createElement(code)).attr(attrName, attrValue);
			}
			function createItemFilm(srcImg, hrefchitiet, filmname){
				var divitem = createDivAtrr('div', 'class', 'item');
				var div2 = createDivAtrr('div', 'class', 'col-xs-12 col-sm-6 col-md-3 item-res');
				var aa = createDivAtrr('a', 'href', '');
				var img = $(document.createElement('img')).attr({
					src: baseUrlImg+srcImg,
					class: 'img-responsive'
				});
				var div3 = createDivAtrr('div', 'class', 'film-content');
				var h4 = createDivAtrr('h4', 'class', 'text-center').text(filmname);
				var a = $(document.createElement('a')).attr({
					class: 'text-center button',
					href: hrefchitiet,
					title: 'xem chi tiết'
				}).text('Chi tiết');
				var btn = $(document.createElement('button')).attr({
					type: 'button',
					title: 'Mua vé',
					class: 'button btn-booking'
				});
				var span = $(document.createElement('span')).text('Mua Vé');
                                var att = $(document.createElement('a')).attr({
                                        href: 'home?controller=schedule'
				});
				divitem.append(div2);
				div2.append(aa);
				div2.append(div3);
				div3.append(h4);
				div3.append(a);
				div3.append(att);
				aa.append(img);
				att.append(btn);
				btn.append(span);
				
				
				return divitem;
			}
			function createUrl(category, page) {
				return baseUrl+category+"?api_key="+api_key+"&page="+page;
			}
			
			function createpPathUrl(url) {
				for(var i= url.length-1; i > 0; i--){
					if( url[i] == '/'){
						return url.substr(0,i+1);
					}
				}
				return null;
			}
			$(window).on('load', function() {
				var page = 1;
				fetch(createUrl('popular', page))
				.then((resp) => resp.json())
				.then( function(data){
					let all = data.results;
					$.each(all, function(key, value){
                                            if(key>=8) exit;
						$('.selection-item').append(createItemFilm(
							value.poster_path,
							createpPathUrl(window.location.href)+"home?controller=detail_film&movies="+value.id,
							value.title
						));
					});
				})
				.catch(function(error) {
//					var err = $(document).createElement('h2').text(error.status_message);
//				    $('.moive-info').append(err);
				}); 
			});
			
		});
