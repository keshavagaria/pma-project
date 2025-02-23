

var chartDataStr=decodeHTML(chartData);
var chartJsonArray=JSON.parse(chartDataStr);

var arrayLength=chartJsonArray.length;
var numericData=[];
var labelData=[];


for(var i=0;i<arrayLength;i++){
	numericData[i]=chartJsonArray[i].value;
	labelData[i]=chartJsonArray[i].label;
}


new Chart(document.getElementById("myPieChart"),{
	type:'pie',
	data:{
	    labels: labelData,
	    datasets: [{
	      label: 'My First dataset',
	      backgroundColor: ["#82ff6b","#ffff6b","#ff0000"],
	      //borderColor: 'rgb(255, 99, 132)',
	      data: numericData
	    }]
	  },	
	  
	options:{
		plugins:{
			title:{
				display:true,
				text:'Project Statuses'
				  }
		       }
	  		}
});


//[{"value":1,"label":"COMPLETED"},{"value":2,"label":"NOTSTARTED"},{"value":3,"label":"INPROGRESS"}]
function decodeHTML(html){
	var txt=document.createElement("textarea");
	txt.innerHTML=html;
	return txt.value;
}