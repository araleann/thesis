$j(function(){
	var funcs = {
		processReport : function processReport(){
			$j(":checked:not(.general)")
				.attr("name", "projectionList");
			
			$j("#report").submit();
		}
	};
	
	$j.extend(window, funcs);
	
	// EVENT FUNCTIONS
	function toggleDetails(){
		$j(this)
			.siblings()	
				.toggle()
				.children(":checkbox")
					.trigger("change");
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
				.attr("disabled", !isChecked);
	}
	
	// ATTACH TO EVENTS
	$j(".entity .general").change(toggleDetails);
	$j(".filterButton").click(addFilter);
	$j(":checkbox:not(.general)").change(toggleFilter);
	
	// MAIN
	GeneralUtils.addDatepicker($j(".date"));
	$j(".filter").attr("disabled", true);
});