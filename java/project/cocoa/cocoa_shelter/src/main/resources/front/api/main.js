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
    return $axios({
        'url': `/bondpair/pet/${id}`,
        'method': 'get',
    })
}


