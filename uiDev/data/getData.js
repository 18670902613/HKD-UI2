import axios from 'axios';

// 请求用户信息
export const getUserData = () => axios.get('../data/user.json');

// 请求部门数据
export const getDepartmentsData = () => axios.get('../data/departments.json');

// 请求通知公告和新闻数据
export const getNewsData = () => axios.get('../data/news.json');

// 请求该用户权限下的所有服务数据
export const getServicesData = () => axios.get('../data/services.json');

// 请求该用户收藏列表
export const getCollectionsData = () => axios.get('../data/collections.json');

// 请求该用户的该服务的菜单列表
export const getLeftMenuData = (serviceID) => axios.get('../data/leftMenu.json');

// 请求评奖评优获奖名单
export const getPjpyHjmdData= () => axios.get('../data/pjpyHjmd.json');