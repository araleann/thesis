$j(function(){
	
	var funcs = {
		clearForm : function clearForm(){
			GeneralUtils.clearForm();
		},
		
		saveCategory : function saveCategory(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/categoryForm.htm"),
					$j("#catForm").serialize(),
					"#categories",
					clearForm);
			
			GeneralUtils.post(postConfig);
		},
		
		voidCategory : function voidCategory(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullCategory.htm"),
					GeneralUtils.voidObj("cid", id),
					"#category" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteCategory : function deleteCategory(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullCategory.htm"),
					GeneralUtils.deleteObj("cid", id),
					"#category" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		editCategory : function editCategory(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editCategory.htm"),
					{ cid : id },
					"#catForm");
			
			GeneralUtils.post(postConfig);
		},
		
		saveExamType : function saveExamType(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/examTypeForm.htm"),
					$j("#typeForm").serialize(),
					"#examTypes",
					clearForm);
			
			GeneralUtils.post(postConfig);
		},
		
		editExamType : function editExamType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editExamType.htm"),
					{ eid : id },
					"#typeForm");
			
			GeneralUtils.post(postConfig);
		},
		
		voidExamType : function voidExamType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullExamType.htm"),
					GeneralUtils.voidObj("eid", id),
					"#type" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteExamType : function deleteExamType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullExamType.htm"),
					GeneralUtils.deleteObj("eid", id),
					"#type" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		saveNoteType : function saveNoteType(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/noteTypeForm.htm"),
					$j("#typeForm").serialize(),
					"#noteTypes",
					clearForm);
			
			GeneralUtils.post(postConfig);
		},
		
		voidNoteType : function voidNoteType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullNoteType.htm"),
					GeneralUtils.voidObj("nid", id),
					"#note" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteNoteType : function deleteNoteType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullNoteType.htm"),
					GeneralUtils.deleteObj("nid", id),
					"#note" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		editNoteType : function editNoteType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editNoteType.htm"),
					{ nid : id },
					"#typeForm");
			
			GeneralUtils.post(postConfig);
		},
		
		saveFeeType : function saveFeeType(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/feeTypeForm.htm"),
					$j("#feeForm").serialize(),
					"#feeTypes",
					clearForm);
			
			GeneralUtils.post(postConfig);
		},
		
		voidFeeType : function voidFeeType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullFeeType.htm"),
					GeneralUtils.voidObj("tid", id),
					"#type" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteFeeType : function deleteFeeType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullFeeType.htm"),
					GeneralUtils.deleteObj("tid", id),
					"#type" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		editFeeType : function editFeeType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editFeeType.htm"),
					{ tid : id },
					"#feeForm");
			
			GeneralUtils.post(postConfig);
		},
		
		saveItemType : function saveItemType(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/itemTypeForm.htm"),
					$j("#typeForm").serialize(),
					"#itemTypes",
					clearForm);
			
			GeneralUtils.post(postConfig);
		},
		
		voidItemType : function voidItemType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullItemType.htm"),
					GeneralUtils.voidObj("tid", id),
					"#type" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteItemType : function deleteItemType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullItemType.htm"),
					GeneralUtils.deleteObj("tid", id),
					"#type" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		editItemType : function editItemType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editItemType.htm"),
					{ tid : id },
					"#typeForm");
			
			GeneralUtils.post(postConfig);
		}
	};
	
	GeneralUtils.addPlaceholderById("category", "Enter a category");
	
	var examPh = {
		examtype : "Exam Type",
		template : "Template for Negative Results"
	}
	GeneralUtils.addPlaceholderById(examPh);
	
	GeneralUtils.addPlaceholderById("notetype", "Enter Note Type");
	
	GeneralUtils.addPlaceholderById("feetype", "Enter Fee Type");
	
	GeneralUtils.addPlaceholderById("itemtype", "Enter an Item Type");
	
	$j.extend(window, funcs);
});