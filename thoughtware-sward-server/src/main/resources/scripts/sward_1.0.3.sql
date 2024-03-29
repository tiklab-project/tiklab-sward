ALTER TABLE wiki_document_template ADD COLUMN detail_text TEXT;

UPDATE "public"."wiki_document_template" SET "name" = '周报模版', "description" = NULL, "details" = '[{"children":[{"text":""}]},{"type":"table","childrenType":"table","children":[{"type":"table-row","childrenType":"table-row","key":"row_7ec03d5c-1e5a-4190-b6c2-498bfb2c1891","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_0beb2732-6c08-497d-a62f-0a0f9be41cfe","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":"姓名"}]}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","childrenType":"table-cell","key":"cell_6169ffe9-eaa3-4340-9cda-913b7097b52b","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":""}]}]}]}],"width":"100px","height":"44px","head":false}]},{"type":"table-row","childrenType":"table-row","key":"row_b81c719d-8c24-4994-94e0-68b451ae76ce","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_2decb82c-6692-462d-991c-96ded214b58c","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":"部门"}]}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","childrenType":"table-cell","key":"cell_6259cec5-3ca5-4d1e-badf-eb0814f9f3aa","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":"技术研发部"}]}]}]}],"width":"100px","height":"44px","head":false}]},{"type":"table-row","childrenType":"table-row","key":"row_57ea921c-ceb3-43bc-af45-b0a1ae2b0452","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_a0a58407-7827-4dbc-952c-b3e5ca118a85","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":"时间"}]}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","childrenType":"table-cell","key":"cell_c54c4713-c824-429f-a23a-39a15cae4dd1","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":"2023.11.20"}]}]}]}],"width":"100px","height":"44px","head":false}]},{"type":"table-row","childrenType":"table-row","key":"row_652e7b62-8b84-4cb4-a46c-b9f7ab05ee59","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_154c6075-2dad-4d31-894f-b4ac87206664","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":"上周工作内容"}]}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","childrenType":"table-cell","key":"cell_1af0a626-80a1-4fd3-8dce-2b4792f63f55","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":""}]}]}]}],"width":"100px","height":"44px","head":false}]},{"type":"table-row","childrenType":"table-row","key":"row_cb2a0f60-6fde-4eac-aa29-4050802d1616","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_5ccbe44e-4e0d-4549-9892-3572a55ae3ee","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":"本周工作计划"}]}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","childrenType":"table-cell","key":"cell_e4f06488-04c4-47bc-986f-3b55301bcfdf","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":"1. 解决缺陷"}]},{"children":[{"text":"2. 验证，发布版本"}]}]}]}],"width":"100px","height":"44px","head":false}]},{"type":"table-row","childrenType":"table-row","key":"row_1fb5c9c1-fb35-4aa4-b730-52514562b29a","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_e6ebb4e0-7f21-494d-b0b6-4bbedf573889","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":"问题"}]}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","childrenType":"table-cell","key":"cell_f87a1c96-0f22-4bbf-8a4c-1fb2d95ffd42","children":[{"type":"table-content","childrenType":"table-content","children":[{"children":[{"text":""}]}]}],"width":"100px","height":"44px","head":false}]},{"type":"table-row","childrenType":"table-row","key":"row_42f01eae-d3a1-44ba-8a52-17bed78f65b9","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_c228e6af-feb2-4f2c-91c0-e2755d8d8060","children":[{"type":"table-content","childrenType":"table-content","children":[{"align":"left","children":[{"children":[{"text":"风险"}]}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","childrenType":"table-cell","key":"cell_76514318-96b2-4746-b7f4-c602868c9d68","children":[{"type":"table-content","childrenType":"table-content","children":[{"children":[{"text":""}]}]}],"width":"100px","height":"44px","head":false}]}],"data":{},"head":2},{"children":[{"text":""}]}]', "sort" = NULL, "icon_url" = '/image/9ba6e989ab50b9ec', "detail_text" = '


姓名



﻿





部门



技术研发部




时间



2023.11.20




上周工作内容



﻿





本周工作计划



1. 解决缺陷

2. 验证，发布版本




问题



﻿





风险



﻿


﻿
' WHERE "id" = '32f87df4';