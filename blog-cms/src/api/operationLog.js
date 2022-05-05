import axios from '@/utils/request'

export function getOperationLogList(queryInfo) {
    return axios({
        url: '/operationLogs',
        method: 'GET',
    })
}

export function deleteOperationLogById(id) {
    return axios({
        url: '/operationLog',
        method: 'DELETE',
        params: {
            id
        }
    })
}