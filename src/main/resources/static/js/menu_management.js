$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/menu_display/",
        dataType: "json",
        cache: false,
        success: function (response) {
            var menu_items_table_data = ""
            // console.log(response)
            var menu_items_obj = response
            for (i = 0; i < menu_items_obj.length; i++) {
                var menu_item_id = menu_items_obj[i].id;
                var name = menu_items_obj[i].name;
                var description = menu_items_obj[i].description;
                var ingredients = menu_items_obj[i].ingredients;
                var price = menu_items_obj[i].price;
                var foodCategory = menu_items_obj[i].foodCategory;
                if(menu_items_obj[i].isVeg){
                    var isVeg = "Yes"
                }
                else{
                    var isVeg = "No"
                }
                var isEnabled = menu_items_obj[i].isEnabled;

                menu_items_table_data += "<tr id='entry" + menu_item_id + "'><td>" + name + "</td><td>" + price + "</td><td>" + foodCategory + "</td><td>" + isVeg + "</td><td><a href='/edit_menu_item/" + menu_item_id + "'>Edit</a></td>" + "<td><a href='#' onclick=deleteThis(" + menu_item_id + ")>Delete</a></td></tr>"

            }
            elementoftables = document.getElementById("menu_body")
            elementoftables.innerHTML = menu_items_table_data
        }
    });
});

function deleteThis(menu_item_id) {
    $.ajax({
        type: "POST",
        url: "/delete_menu_item/",
        data: {"menu_item_id": menu_item_id},
        success: function (response) {
            $("#failureMessage").val('')
            $("#successMessage").val('')

            //alert(response)
            var obj1 = response;
            var semedetails = obj1.datastatus;
            if (obj1.msg == 'Y') {
                vchEntryElement = document.getElementById("entry" + obj1.id)
                vchEntryElement.parentElement.removeChild(vchEntryElement);
                alert("Menu Item Removed Successfully");
            }
        }
    });
}