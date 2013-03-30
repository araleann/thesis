$j(function(){
	var funcs = {
		loadExam : function loadExam(id){
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/loadExam.htm"),
								{ examId : id },
								"#examForm");
			
			GeneralUtils.post(postConfig);
		},
		
		saveExam : functions saveExam(){
			$j("#form")
				.attr("action", GeneralUtils.modulePath("/examForm.htm"))
				.submit();
		},
		
		voidExam : function voidExam(id){
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/nullExam.htm"),
								GeneralUtils.voidObj("eid", id),
								"#exam" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteExam : function deleteExam(id){
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/nullExam.htm"),
								GeneralUtils.deleteObj("eid", id),
								"#exam" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		}
		
		clearForm : function clearForm(){
			GeneralUtils.clearForm();
		}
	};
	
	$j.extend(window, funcs);
});