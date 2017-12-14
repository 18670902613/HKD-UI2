import axios from 'axios';
import qs from 'qs';

// 请求用户信息
export const getUserData = () => axios
  .post("/biz/person/getPersonInfoByPersonID", qs.stringify({
    pCode: 'cp_admin',
    dept:'',//部门过滤
    orderBy:'',//最热，口碑，最新排序
    serviceType:'',//服务类型过滤
  }))
  .then(function (response) {
    let user = response.data.person;
    return user;
  })
  .catch(function (error) {
    return {};
  });

// 请求部门数据
export const getDepartmentsData = () => axios
  .get("/biz/home/getServicesDepartment", qs.stringify({}))
  .then(function (response) {
    let departments = [];
    departments.push({
      pCreatorDeptID: 0,
      pCreatorDeptName: '不限'
    });
    for(let i in response.data.departments){
      departments.push(response.data.departments[i] || {});
    }
    return departments;
  })
  .catch(function (error) {
    return [];
  });

// 请求通知公告和新闻数据
export const getNewsData = () => axios.get('../data/news.json');

// 请求该用户权限下的所有服务数据
export const getServicesData = () => axios
  .post("/biz/home/getServicesBypCode", qs.stringify({
    pCode: 'cp_admin'
  }))
  .then(function (response) {
    let service = {};
    service = response.data.services || {};
    return service;
  })
  .catch(function (error) {
    return [];
  });

// 请求该用户收藏列表
export const getCollectionsData = () => axios
.post("/biz/home/getServiceStore", qs.stringify({
  pCode: 'cp_admin',
  dept:'',//部门过滤
  orderBy:'',//最热，口碑，最新排序
  serviceType:'',//服务类型过滤
}))
.then(function (response) {
  let stores = {};
  stores = response.data.stores || {};
  return stores;
})
.catch(function (error) {
  return [];
});

// 请求该用户的该服务的菜单列表
export const getLeftMenuData = (serviceID) => axios
.post("/biz/home/getServiceMenu", qs.stringify({
  serviceID: ''
}))
.then(function (response) {
  let menu = {};
  debugger
  menu = response.data.menu || {};
  return menu;
})
.catch(function (error) {
  return [];
});

// 请求评奖评优获奖名单
export const getPjpyHjmdData = () => axios.get('../data/pjpyHjmd.json');