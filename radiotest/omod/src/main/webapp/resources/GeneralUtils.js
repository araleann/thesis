$j(function(){
	
	window.GeneralUtils = {
		addPlaceholderById: addPlaceholderById,
		addPlaceholderByName: addPlaceholderByName
	}
	
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
	function addPlaceholderById(id, text) {
		if ($j.isPlainObject(id)) {
			for (x in id) {
				$j("#" + x).attr("placeholder", id[x]);
			}
		} else {
			$j("#" + id).attr("placeholder", text);
		}
	}
	
	/**
	 * Same convention as addPlaceholderById()
	 * 		obj = {
	 * 			name1: text1,
	 * 			name2: text2
	 * 		}
	 */
	function addPlaceholderByName(name, text) {
		if($j.isPlainObject(name)) {
			for (x in name) {
				$j("[name='" + x + "']").attr("placeholder", name[x]);
			}
		} else {
			$j("[name='" + name + "']").attr("placeholder", text);
		}
	}
});