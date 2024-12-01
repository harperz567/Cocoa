
function isValidUsername (str) {
  return ['admin', 'editor'].indexOf(str.trim()) >= 0;
}

function isExternal (path) {
  return /^(https?:|mailto:|tel:)/.test(path);
}

function isUSPhoneNumber(val) {
  const regex = /^\d{10}$/;
  return regex.test(val);
}

}

// Check username
function checkUserName (rule, value, callback){
  if (value == "") {
    callback(new Error("Enter username"))
  } else if (value.length > 20 || value.length <3) {
    callback(new Error("username should be within 3-20"))
  } else {
    callback()
  }
}

// Check name
function checkName (rule, value, callback){
  if (value == "") {
    callback(new Error("Enter name"))
  } else if (value.length > 40) {
    callback(new Error("Should be within 40 characters"))
  } else {
    callback()
  }
}

function checkPhone (rule, value, callback){

  if (value == "") {
    callback(new Error("Enter phone number"))
  } else if (!isCellPhone(value)) {//引入methods中封装的检查手机格式的方法
    callback(new Error("Invalid"))
  } else {
    callback()
  }
}



}