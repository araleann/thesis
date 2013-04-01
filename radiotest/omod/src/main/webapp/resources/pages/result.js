$j(function() {
	var funcs = {
		getExams : function getExams() {
			function formatTable(){
				$j("#ex").dataTable();
			}
			
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/getExamList.htm"), 
								$j("#trans").serialize(),
								"#exams",
								formatTable);

			if ($j("#trans").validationEngine("validate")) {
				GeneralUtils.post(postConfig);
			}
		},

		result : function result(id, count) {
			$j("#examId").val(id);
			$j("#count").val(count);
			$j("#exam")
				.attr("action", GeneralUtils.modulePath("/results.htm"))
				.submit();
		},

		borrow : function borrow(id) {
			var examId = "#exam" + id;
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/borrowResults.htm"),
								{ examId : id },
								examId);

			GeneralUtils.post(postConfig);
		},

		saveDraft : function saveDraft() {
			GeneralUtils.startLoading();
			$j.post(GeneralUtils.modulePath("/resultsForm.htm"), $j("#result").serialize(), function(data) {
				GeneralUtils.stopLoading();
				alert("Saved as draft!");
			});
		},

		save : function save() {
			$j("#draft").val(false);
			
			function alertSave(){
				alert("Saved result!");
			}
			
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/resultsForm.htm"),
								$j("#result").serialize(),
								"#results",
								alertSave);
			
			if($j("#result").validationEngine("validate")){
				GeneralUtils.post(postConfig);
			}
		},

		edit : function edit() {
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/editResultForm.htm"),
								$j("#result").serialize(),
								"#results");
			
			if (confirm("Are you sure you want to edit?")) {
				GeneralUtils.post(postConfig);
			}
		},

		writeTemplate : function writeTemplate() {
			var isNegative = $j("#positive2").attr("checked");
			var $findings = $j("#findings");

			if (isNegative) {
				$findings.val(template);
			} else {
				$findings.val("");
			}
		},
		
		printResult : function printResult() {
			$j("#result")
					.attr("action", GeneralUtils.modulePath("/prtRes.htm"))
					.submit();
		}
	};

	$j.extend(window, funcs);

	// MAIN
	if(GeneralUtils.atPage("resultsForm")){
		ValidationUtils.requireForm("#result");
		ValidationUtils.attachSubmit("#result");
	} else if(GeneralUtils.atPage("patientProfile")){
		ValidationUtils.attachSubmit("#trans");
	}
});