$("#startDate,#endDate").change(function () {
	        var startDate = document.getElementById("startDate").value;
	        var endDate = document.getElementById("endDate").value;
	     
	        if ((Date.parse(endDate) <= Date.parse(startDate))) {
	            alert("End date should be greater than Start date");
	            document.getElementById("endDate").value = "";
	        }

			console.log(startDate);
	    });