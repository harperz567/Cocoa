//获取所有的宠物分类
function categoryListApi() {
    return $axios({
      'url': '/category/list',
      'method': 'get',
    })
  }

//获取宠物分类对应的宠物
function petListApi(data) {
    return $axios({
        'url': '/pet/list',
        'method': 'get',
        params:{...data}
    })
}

//获取宠物分类对应的宠物家族
function bondpairListApi(data) {
    return $axios({
        'url': '/bondpair/list',
        'method': 'get',
        params:{...data}
    })
}

//获取宠物家族的全部宠物
function setPairPetDetailsApi(id) {
    // 调用 $axios 并返回 Promise
    return $axios({
        'url': `/bondpair/${id}`,
        'method': 'get',
    })
    .then(response => {
        console.log("Response data:", response.data);
        //petDetails.value = response.data; // 确认返回数据是否包含 image 属性
        return response; // 返回数据供其他调用链使用
    })
    .catch(error => {
        console.error("API Error:", error);
        throw error; // 如果需要，抛出错误供调用者处理
    });
}



