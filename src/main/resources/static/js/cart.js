//global cart for food items
        var cartDict = [];

        function addToCart(item,btnid,btngrpid,itemid, menuId, price){
            let x = document.getElementById(btnid);
            let y = document.getElementById(btngrpid);
            x.style.display = "none";
            y.style.display = "inline-block";
            y.style.width= "max-content";

            cartDict[item] = [1,menuId,parseFloat(price),parseFloat(price)]  ;
            let z = document.getElementById(itemid).innerHTML = cartDict[item][0];
            cartIconNumber();
        }

        function addOne(item,id){
            cartDict[item][0] = cartDict[item][0] + 1;
			cartDict[item][2] = parseFloat(cartDict[item][3]) * (cartDict[item][0]);
            document.getElementById(id).innerHTML = cartDict[item][0];
            cartIconNumber();
        }
        function cartIconNumber(){
        	let total = 0;
        	for(var key in cartDict){
        		total += cartDict[key][0];
        	}
        	if(total >0)
            document.getElementById("lblCartCount").textContent = total;
        	else {
			document.getElementById("lblCartCount").textContent = '';
			//cartDict = [];
			}
        }
        function removeOne(item,btnid,btngrpid,itemid){
           /* for(var key in cartDict){
                console.log("------>"+key,cartDict[key]);
            }*/
            if(cartDict[item][0] > 1){
                cartDict[item][0] = cartDict[item][0] - 1;
				cartDict[item][2] = parseFloat(cartDict[item][3]) * (cartDict[item][0]);
                document.getElementById(itemid).innerHTML = cartDict[item][0];
            }
            else{
            	delete cartDict[item];
                document.getElementById(btngrpid).style.display = "none";
                document.getElementById(btnid).style.display = "inline-block";
            }
            cartIconNumber();
        }
        function placeOrder(){
            document.getElementById('placeOrderMessage').style.display = "block";
            document.getElementById('place').style.display = "none";
        }
        var modal = document.getElementById("myModal");

        var btn = document.getElementById("myBtn");

        var span = document.getElementsByClassName("close")[0];

        var cartDictCopy = [];
        btn.onclick = function() {
        modal.style.display = "block";
        //document.getElementById('cart').innerHTML = "Item ------ : ------ Count </br>"
        for(var key in cartDict){
          //  document.getElementById('cart').innerHTML += key +" : "+cartDict[key] + "</br>";
        }

///////////////////////

let listName = 'orderItems';
let fieldsNames = ['id', 'item', 'quantity', 'price', 'request'];
let totalAmtVar = 0;
let totalQuantityVar = 0;

 for(var key in cartDict){
	
	let carttot = 0;
	for(let i=0; i<cartDictCopy.length;i++){
		if(cartDictCopy[i] == cartDict[key][1]){
			carttot = carttot+1;
			break;
		}
	}
	
	if(carttot>0){
		document.getElementById(cartDict[key][1] + '.' + 'price').innerHTML = cartDict[key][2];
		document.getElementById(cartDict[key][1] + '.' + 'quantity').innerHTML = cartDict[key][0];
		totalAmtVar= totalAmtVar+cartDict[key][2];
		totalQuantityVar = totalQuantityVar+cartDict[key][0];
	}else{
		let rowIndex = document.querySelectorAll('.item').length; //we can add mock class to each movie-row
		let row = document.createElement('div');
        row.classList.add('row', 'item');
	fieldsNames.forEach((fieldName) => {
            let col = document.createElement('div');
            col.classList.add('col', 'form-group');
            if (fieldName === 'id') {
               // col.classList.add('d-none'); //field with id - hidden
			let p = document.createElement('p');
			p.innerText = rowIndex+1;
            col.appendChild(p);
			let input = document.createElement('input');
            input.type = 'hidden';
            input.classList.add('form-control');
            //input.id = listName + rowIndex + '.' + fieldName;
            input.setAttribute('name', listName + '[' + rowIndex + '].menuItemId.id' );
			input.value = parseInt(cartDict[key][1]);
            col.appendChild(input);
            }
			else if (fieldName === 'item') {
				let p = document.createElement('p');
			p.innerText = key;
			            col.appendChild(p);
				}
			else if (fieldName === 'price') {
				let p = document.createElement('p');
				p.id = cartDict[key][1] + '.' + 'price';
			p.innerText = parseFloat(cartDict[key][2]);
			            col.appendChild(p);
				}
			else if (fieldName === 'quantity') {
				let p = document.createElement('p');
				p.id = cartDict[key][1] + '.' + 'quantity';
			p.innerText = cartDict[key][0];
			            col.appendChild(p);
				let input = document.createElement('input');
            input.type = 'hidden';
            input.classList.add('form-control');
            //input.id = listName + rowIndex + '.' + fieldName;
            input.setAttribute('name', listName + '[' + rowIndex + '].quantity');
			input.value = cartDict[key][0];
            col.appendChild(input);
			}
			else{
            let input = document.createElement('input');
            input.type = 'text';
            input.classList.add('form-control');
            //input.id = listName + rowIndex + '.' + fieldName;
            input.setAttribute('name', listName + '[' + rowIndex + '].note' );
            col.appendChild(input);
			}
            row.appendChild(col);
        });

        document.getElementById('movieList').appendChild(row);
		totalAmtVar= totalAmtVar+cartDict[key][2];
		totalQuantityVar = totalQuantityVar+cartDict[key][0];
		cartDictCopy.push(cartDict[key][1]);
		
		/*let hiddenDiv = document.createElement('div');
		hiddenDiv.style.display = "none";
		let input1 = document.createElement('input');
        input1.type = 'hidden';
		input1.value = document.getElementById("orderStatusBool").value;
        input1.setAttribute('name', listName + '[' + rowIndex + '].isCancelled' );
        hiddenDiv.appendChild(input1);*/

		/*let input2 = document.createElement('input');
        input2.type = 'hidden';
		input2.value = document.getElementById("orderStatus").value;
        input2.setAttribute('name', listName + '[' + rowIndex + '].orderStatus' );
        hiddenDiv.appendChild(input2);*/
		
		}
}


document.getElementById("totalAmt").innerHTML = totalAmtVar;
document.getElementById("totalQuantity").innerHTML = totalQuantityVar;
document.getElementById("totalAmtHidden").value = parseFloat(totalAmtVar);

/////////////////////////


        if(document.getElementById("lblCartCount").textContent == ''){
       		document.getElementById('place').style.display = "none";
        }
        }

       
        span.onclick = function() {
        modal.style.display = "none";
        }

        
        window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
        }
        //modal end

function checkIfEmpty(id){
	
	if( $('#'+id+' > div ').find('div').first().children().length == 0 &&
			$('#'+id+' > div ').find('div').last().children().length == 0){
				document.getElementById(id).parentNode.removeChild(document.getElementById(id));
			}
}

function checkIfNoItemsAvailable(){
	if( $('#menu > div').find(".another-sec").length == 0){
		let outerDiv = document.createElement('div');
		outerDiv.classList.add('menu-w3ls', 'py-5');
		outerDiv.style.fontSize = 'xx-large';
		
		let innerDiv = document.createElement('div');
		innerDiv.innerHTML = "No Items Available";
		
		outerDiv.appendChild(innerDiv);
		document.getElementById("mainDiv").appendChild(outerDiv);
		
	}
}