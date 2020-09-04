$(document).ready(function(){
   
var element = $('#list a');
var offset = 0; 
var stepping = 0.02;
var list = $('#list');
var $list = $(list)

$list.mousemove(function(e){
   var topOfList = $list.eq(0).offset().top
   var listHeight = $list.height()
   stepping = (e.clientY - topOfList) /  listHeight * 0.2 - 0.1;
   
});


for (var i = element.length - 1; i >= 0; i--)
{
   element[i].elemAngle = i * Math.PI * 3 / element.length;
}


setInterval(render, 60);


function render(){
   for (var i = element.length - 1; i >= 0; i--){
       
       var angle = element[i].elemAngle + offset;
       
       x = 120 + Math.sin(angle) * 15;
       y = 45 + Math.cos(angle) * 75;
       size = Math.round(30 - Math.sin(angle) * 30);
       
       var elementCenter = $(element[i]).width() / 2;

       var leftValue = (($list.width()/2) * x / 100 - elementCenter) + "px"

       $(element[i]).css("fontSize", size + "pt");
       $(element[i]).css("opacity",size/100);
       $(element[i]).css("zIndex" ,size);
       $(element[i]).css("left" ,leftValue);
       $(element[i]).css("top", y + "%");
   }
   
   offset += stepping;
}

   
});
var jQueryScriptOutputted = false;
		
		function initJQuery() {
		    
		    if (typeof(jQuery) == 'undefined') {
		    
		        if (!jQueryScriptOutputted) {
		            jQueryScriptOutputted = true;
		            
		            // Primitive way of loading scripts (no library yet)
		            document.write("<scr" + "ipt src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js\"></scr" + "ipt>");
		        }
		        setTimeout("initJQuery()", 50);
		        
		    } else {
		    	
		    	// jQuery way of loading scripts
		    	$.getScript('js/jquery.backgroundPosition.js', function() {
		    		
		    		// Just for demo
		    		$("h2").text('This Browser is using a jQuery fallback for this effect.');
		         
		            // Set CSS in Firefox (Required to use the backgroundPosition js)
					$('#shutter1').css({backgroundPosition: '0px 0px'});
					$('#shutter2').css({backgroundPosition: '0px 0px'});
					$('#shutter3').css({backgroundPosition: '0px 0px'});
					$('#shutter4').css({backgroundPosition: '0px 0px'});
                                        $('#shutter5').css({backgroundPosition: '0px 0px'});
                                        $('#shutter6').css({backgroundPosition: '0px 0px'});
                                        $('#shutter7').css({backgroundPosition: '0px 0px'});
                                        $('#shutter8').css({backgroundPosition: '0px 0px'});
                                        $('#shutter9').css({backgroundPosition: '0px 0px'});
                                        $('#shutter10').css({backgroundPosition: '0px 0px'});
                                        
		
					// Animate the Shutter  
					$("#garagedoor a").hover(function() {	
					      $(this).parent().stop().animate({backgroundPosition: '(0px -100px)'}, 500);
					    }, function() {
					      $(this).parent().stop().animate({backgroundPosition: '(0px 0px)'}, 500);
					});
					
		    	});
		
		    }
		            
		}
		
		if (!Modernizr.csstransitions) {
			initJQuery();
		}