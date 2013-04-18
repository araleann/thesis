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
			var postConfig = GeneralUtils.postConfig(
								savePath,
								$j("#itemForm").serialize(),
								"#items",
								clearForm);
			
			if($j("#itemForm").validationEngine("validate")){
				GeneralUtils.post(postConfig);
			}
		},

		voidItem : function voidItem(id){	
			var postConfig = GeneralUtils.postConfig(
								nullPath,
								GeneralUtils.voidObj("iid", id),
								"#item" + id);
			
			GeneralUtils.post(postConfig);
		},

		deleteItem : function deleteItem(id){	
			function clean(){
				$j("div h4:only-child")
					.parent()
					.remove();
			}
			
			var postConfig = GeneralUtils.postConfig(
								nullPath,
								GeneralUtils.deleteObj("iid", id),
								"#item" + id,
								clean);
			
			if (confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},

		editItem : function editItem(id){
			var postConfig = GeneralUtils.postConfig(
								editPath,
								{ iid : id },
								"#itemForm",
								loadForm);
			
			GeneralUtils.post(postConfig);
		},
		
		getItems : function getItems(){
			function addClass(){
				$j("#items")
					.addClass("patientinputmediummult")
					.addClass("validate[required]");
			}
			
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/getItems.htm"),
								$j("#type").serialize(),
								".items",
								addClass);
			
			GeneralUtils.post(postConfig);
		},

		deleteListItem : function deleteListItem(buttonElem){
			$j(buttonElem)
				.parent()
					.remove();
		},


		addItems : function addItems(){
			if($j("#itemsForm").validationEngine("validate")){
				GeneralUtils.startLoading();
				$j.post(GeneralUtils.modulePath("/addListing.htm"), $j("#items").serialize(), function(data){
					var $listings = $j(".listings", data);
					$listings
						.find("input:text")
							.val("1")
							.focus(function(){
								$j(this).select();
							})
							.addClass("validate[required,custom[integer]]");
					$j("#listings").append($listings.children());
					GeneralUtils.stopLoading();
				});
			}
		},

		updateStock : function updateStock(){
			$j("#listings .item").each(function(i){
				var item = "examItems[" + i + "]";
				$j("input:hidden", this)
					.attr("name", item + ".item");
				$j("input:text", this)
					.attr("name", item + ".quantity");
			});
			
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/updateStock.htm"),
								$j("#itemForm").serialize(),
								"#inventory");
			
			if($j("#itemForm").validationEngine("validate")){
				GeneralUtils.post(postConfig);
			}
		},
		
		addStock : function addStock(){
			$j("#listings .item").each(function(i){
				var list = "listings[" + i + "]";
				$j("input:hidden", this)
					.attr("name", list + ".item");
				$j("input:text", this)
					.attr("name", list + ".quantity");
			});
			
			ValidationUtils.submit("#stockForm");
		}
	}
	
	// MAIN
	function addPlaceholders(){
		GeneralUtils.addPlaceholderById("itemname", "Enter Item Name");
		GeneralUtils.addPlaceholderById("unit", "Enter the Unit");
	}
	
	function loadForm(){
		addPlaceholders();
		ValidationUtils.requireForm("#itemForm");
	}
	
	$j.extend(window, funcs);
	
	if(GeneralUtils.atPage("itemForm")){
		loadForm();
	} else if(GeneralUtils.atPage("resultsForm")){
		ValidationUtils.attachSubmit("#itemForm");
	} else if(GeneralUtils.atPage("stock")){
		ValidationUtils.requireForm("#itemsForm");
		ValidationUtils.attachSubmit("#stockForm");
	} else if(GeneralUtils.atPage("inventory")){
		var today = new Date();
		var dateToday = today.getDate() + "-" + (today.getMonth() + 1) + "-" + today.getFullYear();
		
		var filename = "inventory_" + dateToday;
		var tableConfig = TableUtils.initialize(filename);
		$j("#inv").dataTable(tableConfig);
	}
});