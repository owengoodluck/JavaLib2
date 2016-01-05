function getPrefix(str){
	//list1.itemName -> return list
	var index= str.indexOf('.');
	if(index!=-1){
		return str.substr(0,index-1)
	}else{
		return str;
	}
}

function getSurfix(str){
	//list1.itemName -> return .itemName
	var index= str.indexOf('.');
	if(index!=-1){
		return str.substring(index,str.length);
	}else{
		return str;
	}
}

function getIndex(str){
	//list1.itemName -> return 1
	var index= str.indexOf('.');
	if(index!=-1){
		return str.substr(index-1,1)
	}else{
		return null;
	}
}

function copyValue(item){
	var synchronizeValue = $("#synchronizeBox").val();
	if( $("#synchronizeBox").is(':checked')){
		//alert('checked');
		var thisID = item.id;//list${status.index}.itemName
		var text = item.value;
		var index = getIndex(thisID);
		var prefix = getPrefix(thisID);
		var surfix = getSurfix(thisID);
		var totalRow = $("#myTable tr").length ;//there is a row for submit button
		//$("#"+prefix+2+surfix).val(text) ; //can't do this way as there is special character
		for(var i=index;i<totalRow;i++){
			$( document.getElementById(prefix+i+surfix) ).val(text);
		}
		//alert("#"+prefix+2+surfix );
	}
}

function uniqueValueCheck(itemClass){
	var isUnique = true;
	$(itemClass).each(function(){
		var id1 = this.id;
		var value1 = this.value;
		$(itemClass).each(function(){
			var id2 = this.id;
			var value2 = this.value;
			if( value1 != null && value1.length>0 && id1 != id2  && value1 == value2){
				isUnique = false;
				//$(this).css({"color":"red"});
				$( document.getElementById(id1) ).css({"color":"red"});
			}
		});
	});
	return isUnique;
}

function nullCheck( itemClass ){
	var isNull = false;
	$(itemClass).each(function(){
		var id1 = this.id;
		var value1 = this.value;
		if( value1 == null || value1.length == 0 ){
			alert(id1 +'不能为空');
			this.focus();
			isNull = true;
		}
	});
	return isNull;
}

function submitFormAndGoTo(tabName){
	if(tabName !=null && tabName.length > 0){
		$("#tabName").val(tabName);
	}
	$("#productsForm").submit();
}