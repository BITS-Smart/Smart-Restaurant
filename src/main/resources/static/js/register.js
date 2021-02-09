function addFields(val){
	if(val == "MANAGER" || val =="CHEF" || val=="WAITER"){
		document.getElementById("restaurantDropdown").style.display = "block";
		
		let restDiv = document.createElement('div');
		restDiv.classList.add('form-group');
		restDiv.style.display = "none";
		restDiv.id='restDiv';
		let input1 = document.createElement('input');
        input1.type = 'text';
		input1.id = 'restId';
        input1.setAttribute('name', 'restaurantId.id' );
        restDiv.appendChild(input1);
		document.getElementById('mainDiv').appendChild(restDiv);
	}else{
		document.getElementById("restaurantDropdown").style.display = "none";
		document.getElementById("restaurantDropdown").selectedIndex = "0";
		if(document.getElementById('restDiv') != null)
		document.getElementById('restDiv').parentNode.removeChild(document.getElementById('restDiv'));
	}
	
	if(val == "CUSTOMER"){
		/*document.getElementById("phnNumberDiv").style.display = "block";
		document.getElementById("phnNumberDiv").required = true;*/
		let phnnoDiv = document.createElement('div');
		phnnoDiv.classList.add('form-group');
		phnnoDiv.id='phnNumberDiv';
		let input1 = document.createElement('input');
        input1.type = 'text';
		input1.id = 'phoneNumber';
		input1.required = true;
        input1.setAttribute('name', 'customer.phoneNumber' );
		let x = document.createElement("label");
  		let t = document.createTextNode("Phone Number");
  		x.setAttribute("for", input1.id);
		x.appendChild(t);
		phnnoDiv.appendChild(x);
        phnnoDiv.appendChild(input1);
		document.getElementById('mainDiv').appendChild(phnnoDiv);
	}else if(document.getElementById('phnNumberDiv') != null){
		document.getElementById('phnNumberDiv').parentNode.removeChild(document.getElementById('phnNumberDiv'));
	}
	
	if(val == "DELIVERY_GUY"){
		/*document.getElementById("phnNumberDiv").style.display = "block";
		document.getElementById("phnNumberDiv").required = true;*/
		let dlvryDiv = document.createElement('div');
		dlvryDiv.classList.add('form-group');
		dlvryDiv.style.display = "none";
		dlvryDiv.id='dlvryDiv';
		let input1 = document.createElement('input');
        input1.type = 'text';
		input1.id = 'points';
		input1.value = 0;
        input1.setAttribute('name', 'deliveryGuy.points' );
        dlvryDiv.appendChild(input1);
		document.getElementById('mainDiv').appendChild(dlvryDiv);
	}else if(document.getElementById('dlvryDiv') != null){
		document.getElementById('dlvryDiv').parentNode.removeChild(document.getElementById('dlvryDiv'));
	}
	
}

function addRestaurantId(val1){
	if(document.getElementById('restDiv') != null){
		document.getElementById('restId').value = val1;
	}
}