import axios from 'axios';
import qs from 'qs';

// 请求用户信息
export const getUserData = () => axios
  .post("/biz/person/getPersonInfoByPersonID", qs.stringify({
    pCode: 'cp_admin'
  }))
  .then(function (response) {
    let user = response.data.person;
    return user;
  })
  .catch(function (error) {
    return {};
  });

// 请求部门数据
export const getDepartmentsData = () => axios.get('../data/departments.json');

// 请求通知公告和新闻数据
export const getNewsData = () => axios.get('../data/news.json');

// 请求该用户权限下的所有服务数据
export const getServicesData = () => axios
  .post("/biz/home/getServicesBypCode", qs.stringify({
    pCode: 'cp_admin'
  }))
  .then(function (response) {
    let service = {};
    service = response.data.services.list || {};
    return service;
  })
  .catch(function (error) {
    return [];
  });

// 请求该用户收藏列表
export const getCollectionsData = () => axios.get('../data/collections.json');

// 请求该用户的该服务的菜单列表
export const getLeftMenuData = (serviceID) => axios.get('../data/leftMenu.json');

// 请求评奖评优获奖名单
export const getPjpyHjmdData = () => axios.get('../data/pjpyHjmd.json');