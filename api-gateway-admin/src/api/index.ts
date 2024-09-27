import request from '../utils/request';

const HOST = 'http://localhost:8001';

export const fetchData = () => {
    return request({
        url: './mock/table.json',
        method: 'get'
    });
};

export const fetchUserData = () => {
    return request({
        url: './mock/user.json',
        method: 'get'
    });
};

export const fetchRoleData = () => {
    return request({
        url: './mock/role.json',
        method: 'get'
    });
};

export const gatewayServerData = (query) => {
    return request({
        url: `${HOST}/wg/admin/data/queryGatewayServer?groupId=${query.groupId}&pageIndex=${query.pageIndex}&pageSize=${query.pageSize}`,
        method: 'get'
    })
}

export const gatewayServerDetailData = (query) => {
    return request({
        url: `${HOST}/wg/admin/data/queryGatewayServerDetail?groupId=${query.groupId}&gatewayId=${query.gatewayId}&pageIndex=${query.pageIndex}&pageSize=${query.pageSize}`,
        method: 'get'
    });
};

export const gatewayDistributionData = (query) => {
    return request({
        url: `${HOST}/wg/admin/data/queryGatewayDistribution?groupId=${query.groupId}&gatewayId=${query.gatewayId}&pageIndex=${query.pageIndex}&pageSize=${query.pageSize}`,
        method: 'get'
    });
};

export const applicationSystemData = (query) => {
    return request({
        url: `${HOST}/wg/admin/data/queryApplicationSystem?systemId=${query.systemId}&systemName=${query.systemName}&pageIndex=${query.pageIndex}&pageSize=${query.pageSize}`,
        method: 'get'
    });
};

export const applicationInterfaceData = (query) => {
    return request({
        url: `${HOST}/wg/admin/data/queryApplicationInterface?systemId=${query.systemId}&interfaceId=${query.interfaceId}&pageIndex=${query.pageIndex}&pageSize=${query.pageSize}`,
        method: 'get'
    });
};

export const applicationInterfaceMethodData = (query) => {
    return request({
        url: `${HOST}/wg/admin/data/queryApplicationInterfaceMethod?systemId=${query.systemId}&methodId=${query.methodId}&pageIndex=${query.pageIndex}&pageSize=${query.pageSize}`,
        method: 'get'
    });
};