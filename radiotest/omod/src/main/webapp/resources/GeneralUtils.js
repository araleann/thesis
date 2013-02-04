$j(function(){
	
	window.GeneralUtils = {
		/**
		 * If only adding placeholder to single element,
		 * 		use addPlaceholderById(id, text).
		 * If adding to multiple elements,
		 * 		use addPlaceholderById(obj) where
		 * 		obj = {
		 * 			id1: text1,
		 * 			id2: text2
		 * 		}
		 */
		addPlaceholderById: function addPlaceholderById(id, text) {
			if ($j.isPlainObject(id)) {
				for (x in id) {
					$j("#" + x).attr("placeholder", id[x]);
				}
			} else {
				$j("#" + id).attr("placeholder", text);
			}
		},
		
		/**
		 * Same convention as addPlaceholderById()
		 * 		obj = {
		 * 			name1: text1,
		 * 			name2: text2
		 * 		}
		 */
		addPlaceholderByName: function addPlaceholderByName(name, text) {
			if($j.isPlainObject(name)) {
				for (x in name) {
					$j("[name*='" + x + "']").attr("placeholder", name[x]);
				}
			} else {
				$j("[name*='" + name + "']").attr("placeholder", text);
			}
		},
		
		/**
		 * @param $obj: jQuery object. Object that the datepicker will attach to.
		 * @param options : Object. Custom options for the datepicker. (Optional)
		 * 
		 * Gives default options for the datepicker
		 */
		addDatepicker: function addDatepicker($obj, options){
			if(!options){
				options = {
					changeMonth: true,
					changeYear: true,
					yearRange: "c-100:c",
					minDate: "-100y",
					maxDate: "+1d",
					dateFormat: "dd/mm/yy"
				};
			}
			$obj.datepicker(options);
		}
	}
});