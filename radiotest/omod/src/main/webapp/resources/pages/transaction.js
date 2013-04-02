$j(function(){
	var examIndex = 0;
	var examIndexLabel = 2;
	
	function getExamsEvent(){
		var currIndex = $j(this).attr("name").match(/\d+/)[0];
		var $examDiv = $j("<div id='exam'></div>");
		var postObj = {
			index : currIndex,
			type : $j(this).val()
		};
		
		var getExamsPath = GeneralUtils.modulePath("/getExams.htm") + " select:last-child";
		GeneralUtils.startLoading();
		$examDiv.load(getExamsPath, postObj, function(data){
			var $exam = $j("div#exams" + currIndex);
			var $existing = $j("div#exam", $exam);
			$j("select", $examDiv)
				.addClass("patientinput")
				.addClass("validate[required]");
			if($existing.size()){
				$existing.replaceWith($examDiv);
			} else {
				$exam.append($examDiv);
			}
			
			GeneralUtils.stopLoading();
		});
	}
	
	var funcs = {
		createTransaction : function createTransaction(){
			GeneralUtils.redirect("transExamForm.htm");
		},
		
		viewTransaction : function viewTransaction(id){
			$j("#transId").val(id);
			$j("#transaction")
				.attr("action", GeneralUtils.modulePath("/viewTransaction.htm"))
				.submit();
		},
		
		addNewExam : function addNewExam(){
			examIndex++;
			
			var $examDiv = $j("<div id='exams" + examIndex + "'> Exam " + examIndexLabel +"</div>");
			var $typeDiv = $j("<div id='type'></div>");
			var postObj = { index : examIndex };
			
			var addExamPath = GeneralUtils.modulePath("/addExam.htm") + " select:first-child";
			GeneralUtils.startLoading();
			$typeDiv.load(addExamPath, postObj, function(data){
				$j("select", $typeDiv)
					.addClass("patientinput")
					.addClass("validate[required]");
				
				$typeDiv
					.children('select')
						.change(getExamsEvent);
				$typeDiv.appendTo($examDiv);
				$examDiv.appendTo("#transExam");
				updateDeleteButton();
				
				GeneralUtils.stopLoading();
			});
			
			examIndexLabel++;
		},
		
		deleteExam : function deleteExam(){
			$j("div#exams" + examIndex).remove();
			examIndex--;
			examIndexLabel--;
			updateDeleteButton();
		},
		
		updateDeleteButton : function updateDeleteButton(){
			var $delete = $j("button#delete");
			var disabledState = examIndex == 0;
			var isDisabled = $delete.attr("disabled");
			
			if(disabledState != isDisabled){
				if(disabledState){
					$delete.attr("disabled", "disabled");
				} else {
					$delete.removeAttr("disabled");
				}
			}
		},
		
		saveNote : function saveNote(){
			if($j("#noteForm").validationEngine("validate")){
				GeneralUtils.startLoading();
				$j.post(GeneralUtils.modulePath("/saveNote.htm"), $j("#noteForm").serialize(), function(data){
					var $note = $j("#note", $j(data));
					$note.unwrap();
					$j("#notes").prepend($note);
					
					GeneralUtils.stopLoading();
				});
			}
		}, 
		
		addPayment : function addPayment(){
			function alertPayment(){
				alert("Payment added");
			}
			
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/transactionForm.htm"),
								$j("#payment").serialize(),
								"#paymentDiv",
								alertPayment);
			
			GeneralUtils.post(postConfig);
		}, 
		
		noteTypesEvent : function noteTypesEvent(){
			var $desc = $j("#desc");
			var isHidden = $desc.attr("hidden");
			var others = $j("#noteType").val() === "0";
			
			if (others == isHidden){
				if (isHidden){
					$desc
						.removeAttr("hidden");
				} else {
					$desc
						.attr("hidden", true);
				}
			}
		},
		
		load_assessment : function loadAssessment(){
			var win = window.open(GeneralUtils.modulePath("/sample.htm"), "_blank");
			win.focus();
		},
		
		viewExams : function viewExams(id){
			$j("#transId").val(id);
			$j("#transaction")
				.attr("action", GeneralUtils.modulePath("/viewExams.htm"))
				.submit();
		},

		claim : function claim(id){
			$j("#transId").val(id);
			
			function formatTable(){
				$j("#claim", "#claimDiv").dataTable();
			}
			
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/claimResults.htm"),
								$j("#transaction").serialize(),
								"#claimDiv",
								formatTable);
			
			if(confirm("Confirm claim")){
				GeneralUtils.post(postConfig);
			}
		},
		
		voidTransaction : function voidTransaction(id){
			$j("#transId").val(id);
			
			function formatTable(){
				$j("#expired", "#expiredDiv").dataTable();
			}
			
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/voidTransaction.htm"),
								$j("#transaction").serialize(),
								"#expiredDiv",
								formatTable);
			
			if(confirm("Confirm void of transaction. (Warning: action is irreversible)")){
				GeneralUtils.post(postConfig);
			}
		}
	};
	
	$j.extend(window, funcs);
		
	if(GeneralUtils.atPage("transExamForm")){
		$j("select").change(getExamsEvent);
		ValidationUtils.requireForm("#transExam");
	} else if(GeneralUtils.atPage("transactionForm")){
		ValidationUtils.requireForm("#noteForm");
	}
});