<template>
	<div>
		<div class="container">
			<div class="handle-box">
				<el-select v-model="query.groupId" placeholder="网关分组" class="handle-select mr10">
					<el-option key="1" label="廊坊网关" value="10001"></el-option>
					<el-option key="2" label="亦庄网关" value="10002"></el-option>
					<el-option key="2" label="东丽网关" value="10003"></el-option>
				</el-select>
				<!-- <el-input v-model="query.name" placeholder="应用信息" class="handle-input mr10"></el-input> -->
				<el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
				<el-button type="primary" :icon="Plus" @click="handleAdd">新增</el-button>
			</div>
			<el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
				<el-table-column prop="groupId" label="分组编号" width="128" align="center"></el-table-column>
				<el-table-column prop="groupName" label="分组名称"></el-table-column>
				<el-table-column label="操作" width="220" align="center">
					<template #default="scope">
						<el-button text :icon="Edit" @click="handleEdit(scope.$index, scope.row)" v-permiss="15">
							编辑
						</el-button>
						<el-button text :icon="Delete" class="red" @click="handleDelete(scope.$index)" v-permiss="15">
							删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div class="pagination">
				<el-pagination
					background
					layout="total, prev, pager, next"
					:current-page="query.pageIndex"
					:page-size="query.pageSize"
					:total="pageTotal"
					@current-change="handlePageChange"
				></el-pagination>
			</div>
		</div>

		<!-- 编辑弹出框 -->
		<el-dialog title="编辑" v-model="editVisible" width="30%" v-show="editVisible">
			<el-form label-width="70px">
				<el-form-item label="分组编号">
					<el-input v-model="form.groupId"></el-input>
				</el-form-item>
				<el-form-item label="分组名称">
					<el-input v-model="form.groupName"></el-input>
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="editVisible = false">取 消</el-button>
					<el-button type="primary" @click="saveEdit">确 定</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<script setup lang="ts" name="basetable">
import { ref, reactive } from 'vue';
import { ElMessage, ElMessageBox, TableV2Placeholder } from 'element-plus';
import { Delete, Edit, Search, Plus } from '@element-plus/icons-vue';
import { gatewayServerData } from '../api/index';

interface TableItem {
	groupId: string,
	groupName: string
}

const query = reactive({
	groupId: '',
	pageIndex: 1,
	pageSize: 10
});
const globalParam = {
	showEdit: false
}
const tableData = ref<TableItem[]>([]);
const pageTotal = ref(0);
// 获取表格数据
const getData = () => {
	gatewayServerData(query).then(res => {
		tableData.value = res.data.data;
		pageTotal.value = res.data.total || 50;
	});
};
getData();

// 查询操作
const handleSearch = () => {
	query.pageIndex = 1;
	getData();
};
// 分页导航
const handlePageChange = (val: number) => {
	query.pageIndex = val;
	getData();
};

// 删除操作
const handleDelete = (index: number) => {
	// 二次确认删除
	ElMessageBox.confirm('确定要删除吗？', '提示', {
		type: 'warning'
	})
		.then(() => {
			ElMessage.success('删除成功');
			tableData.value.splice(index, 1);
		})
		.catch(() => {});
};

// 表格编辑时弹窗和保存
const editVisible = ref(false);
let form = reactive({
	groupId: '',
	groupName: ''
});
let idx: number = -1;
const handleEdit = (index, row) => {
	form.groupId = row.groupId;
	form.groupName = row.groupName;
	editVisible.value = true;
	idx = index;
};
const saveEdit = () => {
	tableData.value[idx].groupId = form.groupId;
	tableData.value[idx].groupName = form.groupName;

	editVisible.value = false;
	ElMessage.success(`修改第 ${idx + 1} 行成功`);
};
</script>

<style scoped>
.handle-box {
	margin-bottom: 20px;
}

.handle-select {
	width: 120px;
}

.handle-input {
	width: 300px;
}
.table {
	width: 100%;
	font-size: 14px;
}
.red {
	color: #F56C6C;
}
.mr10 {
	margin-right: 10px;
}
.table-td-thumb {
	display: block;
	margin: auto;
	width: 40px;
	height: 40px;
}
</style>
