<template>
    <div>
        <Card>
            <p slot="title">条件过滤</p>
            <Form :model="formItem" :label-width="80">
                <Row>
                    <Col span="8">
                        <FormItem label="姓名">
                            <AutoComplete
                                v-model="formItem.studentName"
                                :data="formItem.studentNameData"
                                @on-search="handleSearchStudentByName"
                                placeholder="输入姓名第一个字试试">           
                            </AutoComplete>
                        </FormItem>                    
                    </Col>
                    <Col span="8">
                        <FormItem label="奖项">
                            <Select v-model="formItem.jx" filterable>
                                <Option :value="jx" v-for="jx in formItem.AllJx" :key="jx.id">{{ jx }}</Option>                        
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="学年">
                            <Select v-model="formItem.xn">
                                <Option :value="xn" v-for="xn in formItem.AllXn" :key="xn.id">{{ xn }}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="院系">
                            <Select v-model="formItem.yx" filterable>
                                <Option :value="yx" v-for="yx in formItem.AllYx" :key="yx.id">{{ yx }}</Option>                        
                            </Select>
                        </FormItem>               
                    </Col>
                    <Col span="8">
                        <FormItem label="年级">
                            <Select v-model="formItem.nj" >
                                <Option :value="nj" v-for="nj in formItem.AllNj" :key="nj.id">{{ nj }}</Option>                        
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="专业">
                            <Select v-model="formItem.zy" filterable>
                                <Option :value="zy" v-for="zy in formItem.AllZy" :key="zy.id">{{ zy }}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        </Card>
        <Card style="margin-top: 15px;">
            <div slot="title"><i-button type="success" @click="exportData()">导出</i-button></div>
            <Table ref="hjmdTable" border :columns="hjmdcxTableColumns" :data="pjpyHjmdcxData"></Table>       
            <div style="margin: 10px;overflow: hidden">
                <div style="float: right;">
                    <Page :total="100" :current="1" class="custom-page1"></Page>
                </div>
            </div>
        </Card>
    </div>
</template>
<script>
    import { getPjpyHjmdData } from '../../../data/getData.js';
    export default {
        data () {
            return {
                title: '获奖名单查询',
                self: this,
                getPjpyHjmdData:getPjpyHjmdData,
                formItem: {
                    studentName: '',
                    studentNameData: [],
                    jx: '全部',
                    AllJx: [
                        '全部',
                        '国家奖学金',
                        '国家励志奖学金',
                        '特等奖',
                        '一等奖',
                        '二等奖',
                        '三等奖'
                    ],
                    xn: '全部',
                    AllXn: [
                        '全部',
                        '2018',
                        '2017',
                        '2016',
                        '2015'
                    ],
                    yx: '全部',
                    AllYx: [
                        '全部',
                        '数学与计算科学学院',
                        '信息与电气工程学院',
                        '外国语学院',
                        '艺术学院',
                        '物理与电子科学学院',
                        '材料科学与工程学院',
                        '建筑与艺术设计学院',
                        '化学化工学院'
                    ],
                    nj: '全部',
                    AllNj: [
                        '全部',
                        '2017年级',
                        '2016年级',
                        '2015年级'
                    ],
                    zy: '全部',
                    AllZy: [
                        '全部',
                        '马克思主义哲学',
                        '国民经济学',
                        '产业经济学',
                        '国民经济学(学术研究型)'
                    ],                         
                },
                hjmdcxTableColumns: [
                    {
                        title: '学号',
                        key: 'xh',
                        align: 'center'
                    },
                    {
                        title: '姓名',
                        key: 'xm',
                        align: 'center'
                    },
                    {
                        title: '获奖名称',
                        key: 'hjmc',
                        align: 'center'
                    },
                    {
                        title: '院系',
                        key: 'yx',
                        align: 'center'
                    },
                    {
                        title: '班级',
                        key: 'bj',
                        align: 'center'
                    },
                    {
                        title: '奖励金额',
                        key: 'jlje',
                        align: 'center'
                    }
                ],
                pjpyHjmdcxData: []  
            }                
        },
        created (){
            this.getPjpyHjmdData().then((hjmd) => {
                this.pjpyHjmdcxData = [...hjmd.data.data];
            })
        },
        methods: {
            handleSearchStudentByName (value) {
                this.formItem.studentNameData = !this.formItem.studentName ? [] : [
                    this.formItem.studentName,
                    this.formItem.studentName + this.formItem.studentName,
                    this.formItem.studentName + this.formItem.studentName + this.formItem.studentName
                ];
            },
            exportData (){
                this.$refs.hjmdTable.exportCsv({
                    filename: '获奖名单查询'
                });
        }          
        }
    }
</script>
<style scoped lang="less">

</style>
