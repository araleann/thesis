$j(function(){
	var funcs = {
		load_profile : function load_profile(){
			GeneralUtils.redirect("patientProfile.htm");
		},
		
		load_transaction : function load_transaction(){
			GeneralUtils.redirect("transactions.htm");
		},
		
		load_results : function load_results(){
			GeneralUtils.redirect("results.htm");
		}
	};
	
	$j.extend(window, funcs);
});