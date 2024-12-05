// 查询列表接口
const getPetPage = (params) => {
  return $axios({
    url: '/pet/page',
    method: 'get',
    params
  })
}

// 删除接口
const deletePet = (ids) => {
  return $axios({
    url: '/pet',
    method: 'delete',
    params: { ids }
  })
}

// 修改接口
const editPet = (params) => {
  return $axios({
    url: '/pet',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addPet = (params) => {
  return $axios({
    url: '/pet',
    method: 'post',
    data: { ...params }
  })
}

// 查询详情
const queryPetById = (id) => {
  return $axios({
    url: `/pet/${id}`,
    method: 'get'
  })
}

// 获取宠物分类列表
const getCategoryList = (params) => {
  return $axios({
    url: '/category/list',
    method: 'get',
    params
  })
}

// 查宠物列表的接口
const queryPetList = (params) => {
  return $axios({
    url: '/pet/list',
    method: 'get',
    params
  })
}

// 文件down预览
const commonDownload = (params) => {
  return $axios({
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    url: '/common/download',
    method: 'get',
    params
  })
}

// 起售停售---批量起售停售接口
const petStatusByStatus = (params) => {
  return $axios({
    url: `/pet/status/${params.status}`,
    method: 'post',
    params: { ids: params.id }
  })
}