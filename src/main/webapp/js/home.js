/**
 * 
 */
 window.onload = function(){
	getSessVill();
}

//function getSessVill() {
//	let xhttp = new XMLHttpRequest();
//	
//	xhttp.onreadystatechange = function(){
//		
//		
//		if(xhttp.readyState == 4 && xhttp.status==200){
//			let vill = JSON.parse(xhttp.responseText);
//			console.log(vill);
//			/*document.getElementById("welcomeHeader").innerText=`Welcome ${vill.username}`;*/
//		}
//	}
//	
//	xhttp.open("GET", "http://localhost:8080/ERS/getsessionvill.json");
//
//	xhttp.send();
//}


function getSessVill() {
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "http://localhost:8080/ERS/getsessionvill.json", false);
	
	xhttp.onreadystatechange = function() {
		
		if(xhttp.readyState == XMLHttpRequest.DONE && xhttp.status==200) {
			let vill = JSON.parse(xhttp.responseText);
	//		console.log(vill);
//			if(vill === null){
//				alert('No session');
//			}else{
//				alert('session found!');
//			}
		} else {
			alert('please Login First');
			window.location.href = "http://localhost:8080/ERS/html/index.html";

		}
	};
	xhttp.send();
}





$(function() {

$('#tag-form-submit').on('click', function(e) {
    e.preventDefault();
    console.log("here");
   var amount = $("#amount").val();
   var description = $("#description").val();
   var type = $('#type :selected').text();
    $.ajax({
        type: "POST",
        url: "/ERS/createTicket.change",
     data:{"amount":amount,"description":description,"type":type},
        success: function() {
            alert("Success");
             $('#exampleModal').hide();
            /* location.reload();*/
            window.location.href = "http://localhost:8080/ERS/html/displayTicket.html";
        }
       
       
    });
    return false;
});
});