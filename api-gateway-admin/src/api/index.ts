import request from '../utils/request';

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

export const gatewayServerData = () => {
    return request({
        url: './gateway_server.json',
        method: 'get'
    })
}

export const gatewayServerDetailData = () => {
    return request({
        url: './gateway_server_detail.json',
        method: 'get'
    });
};