// 查询列表数据
const getBondpairPage = (params) => {
  return $axios({
    url: '/bondpair/page',
    method: 'get',
    params
  })
}

// 删除数据接口
const deleteBondpair = (ids) => {
  return $axios({
    url: '/bondpair',
    method: 'delete',
    params: { ids }
  })
}

// 修改数据接口
const editBondpair = (params) => {
  return $axios({
    url: '/bondpair',
    method: 'put',
    data: { ...params }
  })
}

// 新增数据接口
const addBondpair = (params) => {
  return $axios({
    url: '/bondpair',
    method: 'post',
    data: { ...params }
  })
}

// 查询详情接口
const queryBondpairById = (id) => {
  return $axios({
    url: `/bondpair/${id}`,
    method: 'get'
  })
}

// 批量起售禁售
const bondpairStatusByStatus = (params) => {
  return $axios({
    url: `/bondpair/status/${params.status}`,
    method: 'post',
    params: { ids: params.ids }
  })
}
