$j(function() {
	var funcs = {
		getExams : function getExams() {
			function formatTable(){
				$j("#ex").dataTable();
				GeneralUtils.stopPropagation("button", "#ex");
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
			GeneralUtils.startLoading();
			$j("#examId").val(id);
			$j("#count").val(count);
			$j("#exam")
				.attr("action", GeneralUtils.modulePath("/results.htm"))
				.submit();
		},

		borrow : function borrow(id) {
			var borrowId = "#borrow" + id;
			
			function initButton(){
				GeneralUtils.stopPropagation("button", borrowId);
			}
			
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/borrowResults.htm"),
								{ examId : id },
								borrowId,
								initButton);

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
			GeneralUtils.startLoading();
			$j("#result")
					.attr("action", GeneralUtils.modulePath("/prtRes.htm"))
					.submit();
		},
		
		writeId : function writeId(id){
			$j("#transId").val(id);
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