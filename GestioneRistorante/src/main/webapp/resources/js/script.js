function apri(url) {
	newin = window
			.open(
					url,
					'titolo',
					'scrollbars=no,resizable=yes, width=400,height=400,status=no,location=no,toolbar=no');
}
function increment(nome) {
    document.getElementById("myNumber"+nome).stepUp();
}
function decrement(nome){
    document.getElementById("myNumber"+nome).stepDown();
}