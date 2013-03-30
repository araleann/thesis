$j(function(){
	
	var savePath = GeneralUtils.modulePath("/itemForm.htm");
	var nullPath = GeneralUtils.modulePath("/nullItem.htm");
	var editPath = GeneralUtils.modulePath("/editItem.htm");

	// FUNCTIONS
	var funcs = {
		clearForm : function clearForm(){
			GeneralUtils.clearForm();
			addPlaceholders();
		},

		saveItem : function saveItem(){	
			var postConfig = {
				path : savePath,
				postObj : $j("#itemForm").serialize(),
				sourceSel : "#items",
				callback : clearForm
			}
			
			GeneralUtils.post(postConfig);
		},

		voidItem : function voidItem(id){	
			var postConfig = {
				path : nullPath,
				postObj : GeneralUtils.voidObj("iid", id),
				sourceSel : "#item" + id
			}
			
			GeneralUtils.post(postConfig);
		},

		deleteItem : function deleteItem(id){	
			var postConfig = {
				path : nullPath,
				postObj : GeneralUtils.deleteObj("iid", id),
				sourceSel : "#item" + id,
				callback : function clean(){
					$j("div h4:only-child")
						.parent()
						.remove();
				}
			}
			
			if (confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},

		editItem : function editItem(id){
			var postConfig = {
				path : editPath,
				postObj : {
					iid : id
				},
				sourceSel : "#itemForm"
			}
			
			GeneralUtils.post(postConfig);
		},
		
		getItems : function getItems(){
			$j.post(GeneralUtils.modulePath("/getItems.htm"), $j("#type").serialize(), function(data){
				var $items = $j(".items", data);
				$j(".items").replaceWith($items);
				$j("#items").addClass("patientinputmediummult");
			});
		},

		deleteListItem : function deleteListItem(buttonElem){
			$j(buttonElem)
				.parent()
					.remove();
		},


		addItems : function addItems(){
			$j.post(GeneralUtils.modulePath("/addListing.htm"), $j("#item").serialize(), function(data){
				var $listings = $j(".listings", data);
				$listings
					.find("input:text")
						.val("1")
						.focus(function(){
							$(this).select();
						});
				$j("#listings").append($listings.children());
			});
		},

		updateStock : function updateStock(){
			$j("#listings .item").each(function(i){
				var item = "examItems[" + i + "]";
				$j("input:hidden", this)
					.attr("name", item + ".item");
				$j("input:text", this)
					.attr("name", item + ".quantity");
			});
			
			$j.post(GeneralUtils.modulePath("/updateStock.htm"), $j("#itemForm").serialize(), function(data){
				console.log(data);
				var $inventory = $j("#inventory", data);
				$j("#inventory").replaceWith($inventory);
			});
		},
		
		addStock : function addStock(){
			$j("#listings .item").each(function(i){
				var list = "listings[" + i + "]";
				$j("input:hidden", this)
					.attr("name", list + ".item");
				$j("input:text", this)
					.attr("name", list + ".quantity");
					
			});
			
			$j("#stockForm").submit();
		}
	}
	
	// MAIN
	function addPlaceholders(){
		GeneralUtils.addPlaceholderById("itemname", "Enter Item Name");
		GeneralUtils.addPlaceholderById("unit", "Enter the Unit");
	}
	
	$j.extend(window, funcs);
	
	addPlaceholders();
});