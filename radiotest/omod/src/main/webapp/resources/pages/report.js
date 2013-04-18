$j(function(){
	function formatDate(date){
		if(date instanceof jQuery){
			date = date.val();
		}
		return date.replace(/\//g, "-");
	}
	
	var funcs = {
		generateReport : function generateReport(){
			if($j("#report").validationEngine("validate")){
				GeneralUtils.startLoading();
				$j.post(
					GeneralUtils.modulePath("/report.htm"),
					$j("#report").serialize(),
					function(csv){
						var tableArr = $j.csv.toArrays(csv);
						var headerArr = tableArr.shift();
						
						var filename = "report_" + formatDate($j("#sdate")) + "_" + formatDate($j("#edate"));
						var tableConfig = TableUtils.initialize(filename);
						tableConfig["aoColumns"] = TableUtils.fixHeaderArray(headerArr);
						tableConfig["aaData"] = tableArr;
						
						var tableDiv = $j("<div><table id='table'></table></div>");
						$j("#table", tableDiv).dataTable(tableConfig);
						
						var dialogConfig = {
							modal : true,
							title : "Generated Report",
							zIndex : 1,
							width : "auto",
							resizable : false
						}
						
						GeneralUtils.stopLoading();
						
						tableDiv.dialog(dialogConfig);
						TableUtils.redraw();
					});
			}
		}
	}
	
	$j.extend(window, funcs);
	
	// EVENT FUNCTIONS
	function toggleDetails(){
		$j(this)
			.siblings()	
				.toggle()
				.children(":checkbox")
					.attr("checked", function(idx, oldAttr){
						return !oldAttr;
					})
					.change();
	}
	
	function addFilter(){
		$j(this)
			.parent()
				.find(".filter")
				.not(this)
					.toggle();
			
	}
	
	function toggleFilter(){
		var $this = $j(this);
		var isChecked = $this.attr("checked");
		
		$this
			.next(".filter")
				.attr("disabled", !isChecked)
				.find(":input")
					.attr("disabled", !isChecked);
	}
	
	// ATTACH TO EVENTS
	$j(".entity .general").change(toggleDetails);
	$j(".filterButton").click(addFilter);
	$j(":checkbox:not(.general)").change(toggleFilter);
	
	// MAIN
	GeneralUtils.addDatepicker($j(".date"));
	$j(".filter")
		.attr("disabled", true)
		.find(":input")
			.attr("disabled", true);
	$j(".default").click();
	$j(".filterButton").click();
	$j(".date")
		.addClass("validate[required]");
	ValidationUtils.attachSubmit("#report");
});