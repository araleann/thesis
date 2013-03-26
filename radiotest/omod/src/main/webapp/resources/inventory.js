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