<!--
 * @Author: 袁婕轩
 * @Date: 2025-01-02 14:57:22
 * @LastEditors: 袁婕轩
 * @LastEditTime: 2025-01-02 15:35:26
 * @Description: 
-->

# category
| 文件        | 说明                         |
|-----------| ---------------------------- |
| WikiCategoryServiceImpl   | 目录接口 |

# confluence
| 文件        | 说明                         |
|-----------| ---------------------------- |
| ConfluenceImportData719ServiceImpl   | confluence 数据导入服务，导入confluence 7.19社区版本数据 |
| ConfluenceImportDataServiceImpl   | confluence 数据导入服务 |
| InvalidXMLCharFilter   | 解析xml |
| SaxParseServiceImpl   | 节点分类 |

# document
| 文件        | 说明                         |
|-----------| ---------------------------- |
| CommentServiceImpl   | 评论接口 |
| DocumentAttachServiceImpl   | 文档附件，暂时不用 |
| DocumentFocusServiceImpl   | 收藏文档 |
| DocumentServiceImpl   | 文档接口 |
| DocumentTemplateServiceImpl   | 文档模版接口 |
| LikeServiceImpl   | 点赞文档 |
| ShareRelationServiceImpl   | 分享关联的目录或者文档 |
| ShareServiceImpl   | 分享接口 |


# node
| 文件        | 说明                         |
|-----------| ---------------------------- |
| NodeServiceImpl   | 节点接口,(文档，目录) |


# repository
| 文件        | 说明                         |
|-----------| ---------------------------- |
| KanassServiceImpl   | 仓库接口 |
| WikiRepositoryFocusServiceImpl   | 知识库收藏接口 |
| WikiRepositoryServiceImpl   | 知识库接口 |

# support
| 文件        | 说明                         |
|-----------| ---------------------------- |
| IconServiceImpl   | 图标接口 |
| RecentServiceImpl   | 最近查看接口 |
| SearchServiceImpl   | 搜索接口 |
| SettingStaticsServiceImpl   | 设置模块统计接口，用于设置首页，弃用 |
| SystemUrlServiceImpl   | 系统集成地址接口 |
| WikiProjectServiceImpl   |  kanass 的项目与事项接口查找 |



# sql

## wiki_repository 知识库
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 知识库id |
| name   | 知识库名称 |
| type_id   | 类型 |
| master   | 负责人 |
| limits   | 可见范围 0 所有人 1 知识库成员 |
| create_time   | 创建时间 |
| icon_url   | 图标 |
| status   | 状态 nomal ：正常 archived ：归档  |
| archived_time   | 归档时间 |
| archived_user_id   | 归档人 |
| archived_desc   | 归档原因 |
| recycle   | 是否回收 0：没有放入回收站 1：放入回收站 |
| recycle_time   | 回收时间 |
| recycle_user_id   | 回收人 |
| description   | 描述 |

## wiki_category 目录
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 目录id |
| name   | 目录名称 |
| repository_id   | 知识库id |
| parent_category_id   | 父级目录id |
| master   | 负责人 |
| update_time   | 更新时间 |
| icon_url   | 图标 |
| sort   | 排序 |
| dimension   | 深度 |
| tree_path   | 上级目录路径 |

## wiki_document 文档
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 文档id |
| name   | 文档名称 |
| type_id   | 类型 |
| category_id   | 目录id |
| master   | 负责人 |
| update_time   | 更新时间 |
| details   | 数据 |
| detail_text | 数据的文本 |
| sort   | 排序 |
| dimension   | 深度 |
| tree_path   | 上级目录路径 |


## wiki_node 节点
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 节点id |
| name   | 节点名称 |
| repository_id   | 知识库 |
| parent_id   | 上级节点 |
| master   | 负责人 |
| create_time | 创建时间 |
| update_time   | 更新时间 |
| sort   | 排序 |
| dimension   | 深度 |
| tree_path   | 上级目录路径 |
| type   | 类型： document： 文档  category： 目录 |
| status   | 状态： nomal ：正常 archived ：归档  |
| archived_time   | 归档时间 |
| archived_user_id   | 归档人 |
| archived_desc   | 归档原因 |
| recycle   | 是否回收 0：没有放入回收站 1：放入回收站 |
| recycle_time   | 回收时间 |
| recycle_user_id   | 回收人 |
| document_type | 文档类型： document ： 文档； markdown ： md文档 |

## wiki_document_attach 文档附件
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 附件id |
| document_id   | 文档id |
| attachment   | 附件名称 |
| sort   | 排序 |

## wiki_document_template 文档模版
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 模版id |
| name   | 模版名称 |
| description   | 描述 |
| details   | 数据 |
| detail_text   | 数据的文本 |
| sort   | 排序 |
| icon_url   | 图标 |


## wiki_comment 评论
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 评论id |
| document_id   | 文档id |
| parent_comment_id   | 父级评论id |
| first_one_comment_id   | 一级评论id |
| details | 详细内容 |
| user_id   | 用户id |
| aim_at_user   | 回复人 |
| create_time   | 创建时间 |
| update_time   | 更新时间 |

## wiki_like 点赞
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 点赞id |
| to_whom_id   | 文档id |
| like_user   | 用户id |
| like_type   | 类型 |
| create_time   | 创建时间 |    

## wiki_share 分享
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 分享id |
| auth_code   | 验证码  |
| create_time   | 创建时间 |
| limits   | 类型，没有用 |

## wiki_recent 最近查看
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 最近查看id |
| name   | 标题 |
| model   | 类型 |
| model_id   | 查看的id |
| repository_id   | 知识库id |
| recent_time   | 创建时间 |

## wiki_repository_focus 关注的知识库
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 关注的知识库id |
| repository_id   | 知识库id |
| master_id   | 用户id |
| sort   | 排序 |

##  wiki_share_relation 分享关联
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 分享关联id |
| type   | 类型 |
| document_id   | 文档id |
| category_id | 目录id |
| node_id | 节点id |
| share_id | 分享id |

## wiki_system_url 系统集成地址
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 系统集成地址id |
| name   | 系统名称 |
| system_url   | 服务端url |
| web_url   | 前端url |

## wiki_icon 图标
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 图标id |
| icon_name   | 图标名称 |
| icon_url   | 图标url |
| icon_type   | 图标类型 |


## wiki_document_focus 文档收藏
| 字段        | 说明                         |
|-----------| ---------------------------- |
| id   | 文档收藏id |
| document_id   | 文档id |
| master_id   | 用户id |
| focus_time   | 关注时间 |
| repository_id | 知识库id | 
| sort   | 排序 |
