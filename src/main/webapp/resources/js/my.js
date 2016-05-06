var Ordered_Size ="";
function LoadModal(path) {  
 $("#Modal_cont").load(path)
 $('#myModal').modal() 	  
}

function ClickOnMySizeButton(size) {	
	Ordered_Size =size; 	 	  	  
}
function ClickOnMyAddToCartButton(path,price) {
	if(Ordered_Size != "") {
	$.post( path, { size: Ordered_Size, price: price} );
	}
	
}

function ClickOnMyCartButton(path) {
	$("#Modal_cont").load(path)
	 $('#myModal').modal()	
}

