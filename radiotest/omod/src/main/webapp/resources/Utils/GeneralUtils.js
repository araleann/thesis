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
		 * @param options : JSON. Custom options for the datepicker. (Optional)
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
		},
		
		/**
		 * @param url: String. Destination url
		 * 
		 * Convenience method for redirection.
		 * Format: /<page name>.htm
		 */
		redirect : function redirect(url){
			window.location = openmrsContextPath + "/module/radiotest/" + url;
		},
		
		/**
		 * @param url: String. Url to wrap -- module url
		 * 
		 * Convenience method: wrapper for module paths
		 * Format: /<page name>.htm
		 */
		modulePath : function modulePath(url){
			return openmrsContextPath + "/module/radiotest" + url;
		},
		
		/**
		 * @param url: String. Url to wrap -- resource url
		 * 
		 * Convenience method: wrapper for resource paths
		 * To use with $.getScript
		 * Format: /<page url>.htm
		 */
		resourcePath : function resourcePath(url){
			return openmrsContextPath + "/moduleResources/radiotest" + url;
		},
		
		/**
		 * @param formSelector: String. (Optional) Selector for form 
		 * 							(see jQuery API for selectors)
		 * 
		 * Convenience method for clearing forms
		 */
		clearForm : function clearForm(formSelector){
			if(!formSelector){
				formSelector = "form";
			}
			
			$j(formSelector)
			.find(":selected, :checked")
				.each(function(i){
					var $this = $j(this);
					if($this.attr("selected")){
						$this.attr("selected", false);
					} else {
						$this.attr("checked", false);
					}
				});
		
			$j(formSelector)
				.find(":input:not(button)")
					.each(function(i){
						$j(this).val("");
					});
			
			$j(":text:first").focus();
		},
		
		/**
		 * @param config: JSON. configuration/settings
		 * 
		 * 		config = {
		 * 			path : String. Target path,
		 * 			postObj : JSON or String. Object to send to server,
		 * 			sourceSel : String. Selector for source element,
		 * 			destSel : String. (Optional) Selector for destination element, default to source element,
		 * 			callback : Function. Function to execute when server processing is finished
		 * 		}
		 */
		post : function post(config){
			var sourceSel = config["sourceSel"];
			var destSel = config["destSel"]? config["destSel"] : sourceSel;
			var callback = config["callback"];
			$j.post(config["path"], config["postObj"], function(data){
				if(sourceSel){
					var $destElem = $j(destSel);
					var $sourceElem = $j(sourceSel, data);
					if($sourceElem.length){
						$destElem.replaceWith($sourceElem);
					} else {
						$destElem.remove();
					}
				}
				
				if (callback){
					callback();
				}
			});
		},
		
		/**
		 * @param All parameters of are the same with configuration for post
		 * 
		 * Convenience method for creating config
		 */
		postConfig : function postConfig(path, postObj, sourceSel, callback, destSel){
			var config = {
				path : path,
				postObj : postObj,
				sourceSel : sourceSel
			}
			
			if(callback){
				config["callback"] = callback;
			}
			
			if(destSel){
				config["destSel"] = destSel;
			}
			
			return config;
		},
		
		/**
		 * @param idName: String. Name of request parameter for the id
		 * @param id: Number. Id of the object to be voided
		 * 
		 * Convenience method for creating a post object for voiding data
		 */
		voidObj : function voidObj(idName, id){
			var obj = {
				action : "void"
			};
			obj[idName] = id;
			
			return obj;
		},
		
		/**
		 * @param idName: String. Name of request parameter for the id
		 * @param id: Number. Id of the object to be deleted
		 */
		deleteObj : function deleteObj(idName, id){
			var obj = {
				action : "delete" 
			};
			obj[idName] = id;
			
			return obj;
		},
		
		/**
		 * @param page: String. Name of page to 'compare' to
		 * 
		 * Convenience method for checking current page is the specified page param
		 */
		atPage : function atPage(page){
			var currPage = document.URL
								.split("/")
								.pop()
									.split(".")
									.shift();
			
			return currPage == page;
		}
	}
});