$j(function() {
	var $form = $j("#trans");
	var template = "${ template }";

	var validObj = {
		transId : [ "integer" ]
	};

	function addValidation() {
		var ruleList = ValidationUtils.initRules(validObj);
		ValidationUtils.attachByName(ruleList);

		$form.validationEngine({
			promptPosition : "bottomRight"
		});
		$form.validationEngine("attach");
	}

	var funcs = {
		getExams : function getExams() {
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/getExamList.htm"), 
								$j("#trans").serialize(),
								"#exams");

			if ($form.validationEngine("validate")) {
				GeneralUtils.post(postConfig);
			}
		},

		result : function result(id, count) {
			$j("#examId").val(id);
			$j("#count").val(count);
			$j("#exam").attr("action", GeneralUtils.modulePath("/results.htm"))
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
			$j.post(GeneralUtils.modulePath("/resultsForm.htm"), $j("#result").serialize(), function(data) {
				alert("Saved as draft!");
			});
		},

		save : function save() {
			$j("#draft").val(false);
			$j.post(GeneralUtils.modulePath("/resultsForm.htm"), $j("#result").serialize(),
					function(data) {
						alert("Saved!");
						var $results = $j("#results", $j(data));
						$j("#results").replaceWith($results);
					});
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
	addValidation();
});