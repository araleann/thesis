$j(function(){
	window.TableUtils = {
		/**
		 * Object for table initialization
		 */
		initObj : {
			sDom : 'RT<"clear">lfrtip',
			oTableTools : {
				sSwfPath : "/openmrs-standalone/moduleResources/radiotest/TableTools-2.1.5/media/swf/copy_csv_xls_pdf.swf",
				aButtons : [ 
				            	{
				            		sExtends : "csv",
				            		sFileName : "*.csv"
				            	},
				            	{
				            		sExtends : "xls",
				            		sFileName : "*.xls"
				            	},
				            	{
				            		sExtends : "pdf",
				            		sPdfOrientation : "landscape"
				            	}
				           ]
			},
			bDestroy : true,
			aaSorting : []
		},
		/**
		 * @param headArr : Array. Contains the table headers
		 * @param config : JSON. In the following format:
		 * 		var	config =	{
		 * 							"header" : {
		 * 								sClass : "class" -- HTML class attribute
		 * 								sName : "name" -- HTML name attribute
		 * 								sWidth : 20px -- width of column
		 * 								(See http://www.datatables.net/usage/columns
		 * 									for more configurable attributes)
		 * 							}
		 * 						};
		 */
		fixHeaderArray : function fixHeaderArray(headArr, config){
			var arr = [];
			for(i in headArr){
				var header = headArr[i];
				var headObj = { sTitle : header };
				
				if(config){
					var conf = config[header];
					if(conf){
						$j.extend(headObj, conf);
					}
				}
				
				arr.push(headObj)
			}
			
			return arr;
		}
	}
});