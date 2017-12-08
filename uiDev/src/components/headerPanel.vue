<template>
    <div class="layout-header">
        <!-- LOGO 开始 -->
        <div class="layout-logo">
            <router-link to="/home">
                <img src="../images/logo_04.png">
            </router-link>
        </div>
        <!-- LOGO 结束 -->
        <div class="layout-nav">
            <Menu mode="horizontal" class="header-menu" theme="light">
                <div v-show="visitor">
                    <MenuItem name="login"> 
                        <router-link to="/registe"> 注册</router-link>
                    </MenuItem>                   
                    <MenuItem name="1">
                        <router-link to="/login">
                            <Icon type="person"></Icon>
                            登录
                        </router-link>
                    </MenuItem>
                </div> 
                <!-- 个人中心menu 开始  -->          
                <MenuItem name="userCenter" v-show="!visitor"> 
                    <Dropdown trigger="click" style="margin-left: 20px">
                    <a href="javascript:void(0)">
                        <Badge :count="user.msgCount">
                            <img :src="user.image" style="width:30px;">
                        </Badge>
                        <span v-text="user.name" style="margin-left:5px;"></span>
                    </a>
                    <Dropdown-menu slot="list">
                        <Dropdown-item @click.native="showCollectionsModal">我的收藏</Dropdown-item>                       
                        <Dropdown-item>个人中心</Dropdown-item>                       
                    </Dropdown-menu>
                    </Dropdown>                                                              
                </MenuItem>
                <!-- 个人中心menu 结束  -->  
                <!-- 搜索框 开始 -->
                <MenuItem name="serarch" class="search-service">                                            
                    <i-input 
                        v-model="searchInputVal" 
                        type="text" placeholder="服务内容" 
                        v-show="showSearch" 
                        :autofocus="true"
                        @on-blur="searchInputBlur" 
                        @keyup.native.esc="searchInputBlur" 
                        @on-enter="doSearchEvent">                                    
                            <Icon type="ios-search" slot="prepend" @click.native="doSearchEvent"></Icon>
                    </i-input>                                       
                    <Tooltip content="搜索服务" placement="left">
                        <Icon 
                            type="ios-search" 
                            class="search-service-icon" 
                            v-show="!showSearch" 
                            @click.native="showSearch=!showSearch">                           
                        </Icon>
                    </Tooltip>
                </MenuItem> 
                <!-- 搜索框 结束 -->   
            </Menu>     
        </div>
        <div class="clearfix"></div>
        <!-- 搜索结果弹窗 开始 -->
        <Modal 
            title="您搜索到的结果如下" 
            :value.sync="showModalSearchResult" 
            width="900" 
            @on-cancel="closeSearchModal" 
            class-name="vertical-center-modal" 
            class="search-result-modal">               
            <Card 
                v-for="serv in currPageSearchResult" 
                :key="serv.id" 
                class="service-card">
                <router-link :to="'/service/' + serv.id">
                    <div class="service-icon">
                        <img :src="serv.imgUrl">
                    </div>
                    <div class="service-title">
                        {{ serv.title }}
                    </div> 
                    <div class="service-raty">                           
                        <Rate allow-half :value.sync="serv.raty" disabled></Rate>
                    </div>                   
                    <p style="font-size:12px;">{{ serv.funText }}</p>
                </router-link>
            </Card> 
            <div class="no-result" v-show="noSearchResult">
                <div class="no-result-msg">很遗憾！没有找到您要的服务...</div>
            </div>
            <div style="text-align:center;position:absolute;bottom:15px;width:100%;">
                <transition name="slide-fade">
                    <Page 
                        v-if="searchResult.total > 8" 
                        :total="searchResult.total" 
                        :page-size="searchResult.pageSize" 
                        :current="searchResult.currPage" 
                        @on-change="resultChangePage" 
                        class="home-page">
                    </Page>
                </transition>
            </div>                 
        </Modal>
        <!-- 搜索结果弹窗 结束 -->
        <!-- 我的收藏弹窗 开始 -->
        <Modal 
            title="我的收藏"
            :value.sync="showCollectionServ" 
            width="900" 
            @on-cancel="closeCollectionsModal" 
            class-name="vertical-center-modal" 
            class="collections-modal"
            :class="{ edit: batchOperation } ">  
            <Card 
                v-for="serv in currPageCollectionServ" 
                :key="serv.id" 
                class="service-card">
                <router-link :to="'/service/' + serv.id">
                    <div class="service-icon">
                        <img :src="serv.imgUrl">
                    </div>
                    <div class="service-title">
                        {{ serv.title }}
                    </div> 
                    <div class="service-raty">                           
                        <Rate allow-half :value.sync="serv.raty" disabled></Rate>
                    </div>                   
                    <p style="font-size:12px;">{{ serv.funText }}</p>
                    <Tooltip v-show="!batchOperation" class="btn-collection" content="取消收藏" placement="bottom">
                        <Icon 
                           @click.prevent.native="unCollectionServEvent(serv.id)" 
                           type="close-circled">                          
                       </Icon> 
                   </Tooltip>
                   <Tooltip 
                        v-show="batchOperation" 
                        class="btn-collection-batch" 
                        :content="inSelectedCollects(serv.id) ? '取消选择' : '选择'" 
                        placement="bottom">
                        <Icon 
                            :type="inSelectedCollects(serv.id) ? 'checkmark-circled' : 'android-radio-button-off'" 
                            :style="{ padding: batchOperation ? '10px 10px 248px 170px' : '10px' }" 
                            @click.prevent.native="singleSelectEvent(serv.id)">                                
                        </Icon> 
                   </Tooltip>                       
                </router-link>
            </Card> 
            <div 
                class="operations" 
                :style="{ 
                    backgroundColor: batchOperation ? 'rgba(0, 0, 0, .3)' : '#e5e5e5', 
                    color: batchOperation ? '#fff' : '#44b549' }"> 
                <Tooltip v-show="batchOperation" content="完成" placement="bottom">
                    <Icon 
                        type="android-done" style="float:left;" 
                        @click.prevent.native="batchOperDoneEvent">
                    </Icon>                     
                </Tooltip>                                     
                <Tooltip v-show="!batchOperation && !noCollections" style="float:right;" content="批量操作" placement="bottom">
                    <Icon 
                        type="compose" 
                        @click.prevent.native="batchOperationEvent">                            
                    </Icon>
                </Tooltip>
                <Tooltip v-show="batchOperation" :content="selectAllCollects ? '全不选' : '全选'" style="float:right;" placement="bottom">
                    <Icon 
                        :type="selectAllCollects ? 'checkmark-circled' : 'android-radio-button-off'"                             
                        @click.prevent.native="selectAllCollectsFun">
                    </Icon>
                </Tooltip>                    
            </div>
            <div class="no-result" v-show="noCollections">
                <div class="no-result-msg">您没有收藏任何服务...</div>
            </div>
            <div style="text-align:center;position:absolute;bottom:10px;width:100%;">
                <transition name="slide-fade">
                    <Page 
                        v-if="collectionServ.total > 8" 
                        :total="collectionServ.total" 
                        :page-size="collectionServ.pageSize" 
                        :current="collectionServ.currPage" 
                        @on-change="collectionsChangePage" 
                        class="home-page">
                    </Page>
                </transition>
            </div>                 
        </Modal>
        <!-- 我的收藏弹窗 结束 -->
        <!-- 删除确认弹窗 开始 -->
        <Modal            
            :value.sync="showUnCllectionModal"
            class-name="vertical-center-modal"
            @on-cancel="escRemoveCollectEvent" 
            width="300">
                <div style="font-size:16px;color:#f60;text-align:center">                       
                    <Icon type="information-circled"></Icon>
                    <span>删除确认</span>
                </div>
                <div slot="footer">
                    <i-button type="error" size="large" long :loading="modalLoading" @click="removeCollection">删除</i-button>
                </div>
        </Modal>
        <!-- 删除确认弹窗 结束 -->
    </div>
</template>
<script>
    import { getUserData, getServicesData, getCollectionsData } from '../../data/getData.js';
    self = this;
    export default {
        props: {
            // 登录状态
            visitor: { 
                type: Boolean,
                default: false,
            }         
        },
        created() {
            this.searchResultReset(); // 搜索结果数据重置  
            this.getUserData().then((user) => {
                 // 用户信息
                this.user = user.data.data;
            }); 
            this.getServicesData().then((service) => {
                // 该用户权限下的所有服务数据
                this.servicesData.data = service.data.data;
                this.servicesData.total = this.servicesData.data.length;
            });
        },
        data() {
            return {
                user: {},
                servicesData: {
                    total: 0,
                    currPage: 1,
                    pageSize: 10,
                    data: []
                },
                showSearch: false, // 搜索框显示
                searchAutofocus: true, // 搜索框自动获取光标
                showModalSearchResult: false, // 搜索结果弹窗显示
                showCollectionServ: false, // 我的收藏弹窗显示
                showUnCllectionModal: false, // 取消收藏确认弹窗
                modalLoading: false, // loading图标状态
                batchOperation: false, // 批量操作状态
                selectedCollects: [], // 取消收藏是暂存服务ID
                selectAllCollects: false,
                searchInputVal: '', // 搜索关键字
                getUserData: getUserData, // 请求用户数据方法
                getServicesData: getServicesData, // 请求该用户权限下的所有服务数据方法
                getCollectionsData: getCollectionsData, // 请求收藏列表的方法
                // 用户收藏的服务数据
                collectionServ: {
                    total: 0,
                    currPage: 1,
                    pageSize: 8,
                    data: []
                },
                // 搜索到的服务数据
                searchResult: {
                    total: 0,
                    currPage: 1,
                    pageSize: 8,
                    data: []
                }
            }
        },
        computed: {
            // 搜索到的服务当前页数据
            currPageSearchResult: function() {
                return this.searchResult.data.slice((this.searchResult.currPage - 1) * this.searchResult.pageSize, this.searchResult.currPage * this.searchResult.pageSize);
            },
            // 收藏的服务当前页数据
            currPageCollectionServ: function() {
                return this.collectionServ.data.slice((this.collectionServ.currPage - 1) * this.collectionServ.pageSize, this.collectionServ.currPage * this.collectionServ.pageSize);
            },
            // 有无搜索结果
            noSearchResult: function() {
                return this.searchResult.total < 1;
            },
            // 有无收藏
            noCollections: function() {
                return this.collectionServ.total < 1;
            },
            // 是否全选了所有收藏
            isSelectAllCollects: function() {
                return this.selectedCollects.length == this.collectionServ.data.length;
            }
        },
        watch:{
            $route:function(){
                this.showModalSearchResult = false;
                this.showCollectionServ = false;
            }
        },
        methods: {
            // 执行搜索
            doSearchEvent() {
                if (this.searchInputVal) {
                    // 按搜索条件过滤
                    this.searchResult.data = [...this.filterBy(this.servicesData.data, this.searchInputVal)];
                    this.searchResult.total = this.searchResult.data.length;
                    this.searchResult.currPage = 1;
                    this.searchResult.pageSize = 8;
                    // 搜索按钮UI交互
                    [this.showSearch, this.showModalSearchResult] = [!this.showSearch, true];
                }
            },
            // 搜索结果切换分页
            resultChangePage(e) {
                this.searchResult.currPage = e;
            },
            // 取消搜索
            searchInputBlur() {
                this.showSearch = false;
            },
            // 关闭搜索结果弹窗
            closeSearchModal() {
                this.showModalSearchResult = false;
                // searchResult重置
                this.searchResultReset();
                this.searchInputVal = '';
            },
            // 搜索结果数据重置
            searchResultReset() {
                this.searchResult.data = [...this.servicesData.data];
                this.searchResult.total = this.searchResult.length;
                this.searchResult.currPage = 1;
                this.searchResult.pageSize = 8;
            },
            // 显示收藏列表弹窗
            showCollectionsModal() {
                let self = this;
                this.getCollectionsData().then(function(response) {
                    self.collectionServReset(response.data.data);
                })
                this.showCollectionServ = true;
                this.batchOperation = false;
            },
            // 关闭收藏列表弹窗
            closeCollectionsModal() {
                this.batchOperation = false;
                this.selectedCollects = [];
                this.showCollectionServ = false;
            },
            // 收藏切换分页
            collectionsChangePage(e) {
                this.collectionServ.currPage = e;
            },
            // 收藏列表数据重置
            collectionServReset(aCollectionsIDs) {
                this.collectionServ.data = [];
                for (let id of aCollectionsIDs) {
                    for (let service of this.servicesData.data) {
                        if (id == service.id) {
                            this.collectionServ.data.push(service);
                            break;
                        }
                    }
                }
                this.collectionServ.total = this.collectionServ.data.length;
                this.collectionServ.currPage = 1;
            },
            // 批量删除时单个选择
            singleSelectEvent(serviceId) {
                let index = this.selectedCollects.findIndex(function(value, index, arr) {
                    return value == serviceId;
                });
                if (index == -1) {
                    this.selectedCollects.push(serviceId);
                } else {
                    this.selectedCollects.splice(index, 1);
                }
                this.selectAllCollects = this.isSelectAllCollects ?  true : false;
            },
            // 放弃取消收藏          
            escRemoveCollectEvent() {
                this.showUnCllectionModal = false;
                this.selectedCollects = [];
            },
            // 开始批量操作状态
            batchOperationEvent() {
                this.batchOperation = true;
            },
            // 批量删除是判断是否勾选
            inSelectedCollects(serviceId) {
                return this.selectedCollects.findIndex(function(value, index, arr) {
                    return value == serviceId;
                }) != -1;
            },
            // 单个取消收藏
            unCollectionServEvent(id) {
                this.selectedCollects = [];
                this.selectedCollects.push(id);
                this.showUnCllectionModal = true;
                this.modalLoading = false;
            },
            // 批量删除时全选或全不选
            selectAllCollectsFun() {
                this.selectAllCollects = !this.selectAllCollects;
                this.selectedCollects = [];
                if (this.selectAllCollects) {
                    for (let service of this.collectionServ.data) {
                        this.selectedCollects.push(service.id)
                    }
                }
            },
            // 批量删除完成
            batchOperDoneEvent() {
                this.showUnCllectionModal = true;
                this.modalLoading = false;
            },
            // 执行删除收藏的服务
            removeCollection() {
                if (this.selectedCollects.length < 1) {
                    this.batchOperation = false;
                    this.showUnCllectionModal = false;
                    this.$Message.warning('没有选中。');
                    return;
                };                
                this.modalLoading = true;
                let self = this;
                for (let id of this.selectedCollects) {
                    this.collectionServ.data.forEach(function(service, i) {
                        if (service.id == id) {
                            self.collectionServ.data.splice(i, 1);
                            return;
                        }
                    })
                }
                this.collectionServ.total = this.collectionServ.data.length;
                this.collectionServ.currPage = 1;
                this.batchOperation = false;
                this.selectedCollects = [];
                this.showUnCllectionModal = false;
                this.$Message.success('删除成功。');
            }
        }
    }
</script>
<style lang="less">
    .header-menu {
        &.ivu-menu-horizontal {
            height: 64px;
            .ivu-menu-item,
            .ivu-menu-submenu {
                float: right;

                border: none;

                color: #44b549;

                transition: inherit;
                a {
                    font-size: 16px;

                    color: #44b549;
                }
                &:hover {
                    border: none;

                    color: #484848;
                    a {
                        color: #484848;
                    }
                }
            }
            &:after {
                height: 0;
            }
        }
    }
    .layout-header {
        margin: 0 auto;
        border-top: 4px solid #44b549;
        border-bottom: 1px solid #e5e5e5;
        width: 100%;

        .layout-logo {
            float: left;

            margin-left: 15px;
            width: 350px;
            height: 60px;
        }
        .layout-nav {
            float: right;

            width: 420px;
            .search-service {
                height: 60px;
                .ivu-input-group {
                    top: 13px;
                }
                .ivu-icon {
                    font-size: 22px;
                    &.search-service-icon {
                        vertical-align: middle;
                    }
                    &:hover {
                        font-weight: 600;
                    }
                }
            }
        }
    }
    .search-service {
        .ivu-input-group {
            .ivu-input,
            .ivu-input-group-prepend {
                border-color: #444;
                border-radius: 16px;

                color: #fff;
                background-color: #666;
            }
            .ivu-input {
                &:focus {
                    box-shadow: 0 0 0 2px rgba(45, 240, 87, .3);
                }
            }
        }
    }
    .search-result-modal,
    .collections-modal {
        .ivu-modal-content {
            border-top: 4px solid #44b549;
            border-radius: 0;

            background-color: #e5e5e5;
            .ivu-modal-header {
                text-align: center;

                color: #353535;
                background-color: #fff;
                .ivu-modal-header-inner {
                    font-size: 20px;
                    font-weight: 100;
                }
            }
            .ivu-modal-body {
                position: relative;

                padding-top: 35px;
                height: 684px;

                overlfow: hidden;
                .operations {
                    overflow: hidden;

                    position: absolute;
                    top: 0;
                    left: 0;

                    padding: 5px 30px;
                    width: 100%;

                    font-size: 16px;
                    line-height: 16px;
                    .ivu-icon {
                        font-size: 20px;
                    }
                }
            }
            .ivu-modal-footer {
                display: none;
            }
        }
        &.edit {
            .ivu-modal-content {
                background-color: #444;
                .ivu-modal-body {
                    .service-card {
                        border-color: #fff;

                        background-color: #666;
                        a {
                            color: #44b549;
                            .btn-collection-batch {
                                display: block;

                                color: #fff;
                            }
                        }
                        &:hover {
                            border-color: #44b549;

                            box-shadow: none;
                        }
                    }
                }
            }
        }
    }

</style>
