$j(function(){
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
});