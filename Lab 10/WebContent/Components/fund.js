$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateFundForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "ItemsAPI", 
 type : type, 
 data : $("#formFund").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onFundSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onFundSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divFundsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidFundIDSave").val(""); 
 $("#formFund")[0].reset(); 
}




// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 

 $("#hidFundIDSave").val($(this).data("fundid")); 
 $("#funderName").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#funderAddress").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#companyName").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#companyAddress").val($(this).closest("tr").find('td:eq(3)').text());
 $("#funderEmail").val($(this).closest("tr").find('td:eq(4)').text());
 $("#funderPhone").val($(this).closest("tr").find('td:eq(5)').text());
 $("#fundAmount").val($(this).closest("tr").find('td:eq(6)').text()); 
}); 


$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "FundsAPI", 
 type : "DELETE", 
 data : "fundID=" + $(this).data("fundid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onFundDeleteComplete(response.responseText, status); 
 } 
 }); 
});
function onFundDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divFundsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 }
 }
// CLIENT-MODEL================================================================
function validateFundForm() 
{ 
// FUNDER NAME
if ($("#funderName").val().trim() == "") 
 { 
 return "Insert Funder Name."; 
 } 
// FUNDER ADDRESS
if ($("#funderAddress").val().trim() == "") 
 { 
 return "Insert Funder Address."; 
 }
 // COMPANY NAME------------------------
if ($("#companyName").val().trim() == "") 
 { 
 return "Insert Company Name."; 
 }
 // COMPANY ADDRESS------------------------
if ($("#companyAddress").val().trim() == "") 
 { 
 return "Insert Company Address."; 
 }  
 // FUNDER EMAIL------------------------
if ($("#funderEmail").val().trim() == "") 
 { 
 return "Insert Funder Email."; 
 }
 // FUNDER PHONE------------------------
if ($("#funderPhone").val().trim() == "") 
 { 
 return "Insert Funder Phone."; 
 }   
// AMOUNT-------------------------------
if ($("#fundAmount").val().trim() == "") 
 { 
 return "Insert Fund Amount."; 
 } 
// is numerical value
var tmpAmount = $("#fundAmount").val().trim(); 
if (!$.isNumeric(tmpAmount)) 
 { 
 return "Insert a numerical value for Fund Amount."; 
 } 
// convert to decimal amount
 $("#fundAmount").val(parseFloat(tmpAmount).toFixed(2)); 

return true; 
}/**
 * 
 *//**
 * 
 */