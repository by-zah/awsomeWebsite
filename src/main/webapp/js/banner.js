var imgHead = [
			"img/banner.jpg", 
			"img/banner1.jpg", 
			"img/banner2.jpg", 
			"img/banner3.jpg"
		], i=1;
	function csaHead(){

		if(i > (imgHead.length-1)){ 
			$('.content-banner').animate({'opacity':'0'},400,function(){
				i=1;
				$('.content-banner').css({'background':'url('+imgHead[0]+')', 'background-position':'center', 'background-size':'100%', 'background-repeat':'no-repeat'});
			});
			$('.content-banner').animate({'opacity':'1'},400);
		}else{
			$('.content-banner').animate({'opacity':'0'},400,function(){
				$('.content-banner').css({'background':'url('+imgHead[i]+')', 'background-position':'center', 'background-size':'100%', 'background-repeat':'no-repeat'});
				i++;
			});
			$('.content-banner').animate({'opacity':'1'},400);
		}
		
	}
	var intervalCsaHead = setInterval(csaHead,8000);
