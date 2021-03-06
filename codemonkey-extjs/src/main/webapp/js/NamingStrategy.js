var NamingStrategy = {
	
	url : function(modelName , actionName){
		var ctxElm = document.getElementById("ctx");
		actionName = actionName || 'index';
		var root = ctxElm && ctxElm.value ?  ctxElm.value : ''; 
		return root + '/app/ext/' + Ext.String.uncapitalize(modelName) + '/' + actionName;
	},
	
	store : function(modelName){
		return this.capitalize(modelName) + 'Store';
	},
	
	editView : function(modelName){
		return this.capitalize(modelName + '.Edit');
	},
	
	listView : function(modelName){
		return this.capitalize(modelName + '.List');
	},
	
	grid : function(modelName){
		return this.uncapitalize(modelName) + 'Grid';
	},
	
	editUrl : function (detailModelName , id){
		return this.url(this.uncapitalize(detailModelName) , 'edit') + '/' + id;
	},
	
	detailUrl : function(detailModelName){
		return this.url(this.uncapitalize(detailModelName) , 'index');
	},
	
	xtype : function(modelName){
		return modelName.toLowerCase();
	},
	
	listUrl : function(modelName){
		var listModel = this.uncapitalize(modelName) + 'List';
		return this.url( listModel , 'index');
	},
	
	form : function(modelName){
		return this.uncapitalize(modelName) + 'Form';
	},
	
	uncapitalize : function(str){
		return Ext.String.uncapitalize(str);
	},
	
	capitalize : function(str){
		return Ext.String.capitalize(str);
	},
	
	windowId : function(moduleId){
		return moduleId + '_window';
	},
	
	formId : function(moduleId){
		return this.windowId(moduleId) + '_form';
	}
	
};
var NS = NamingStrategy;