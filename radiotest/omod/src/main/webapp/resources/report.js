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
				.children()
					.attr("checked", function(idx, oldAttr){
						return !oldAttr;
					});
	}
	
	function addFilter(){
		$j(this)
			.parent()
				.find(":input:not(:checkbox)")
				.not(this)
					.toggle();
			
	}
	
	// ATTACH TO EVENTS
	$j(".entity > :checkbox").change(toggleDetails);
	$j(".filter").click(addFilter);
});