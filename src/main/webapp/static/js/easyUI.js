$.extend($.fn.validatebox.defaults.rules, {   
     mobile: {   
        validator: function(value){   
              return /^1[3,5,7,8][0-9]{9}$/i.test($.trim(value));   
          },   
         message: '手机号码格式不正确'  
      }   
 });  

