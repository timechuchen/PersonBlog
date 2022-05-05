import axios from '@/utils/request'

export function getLoginLogList() {
    return axios({
        url: '/loginLogs',
        method: 'GET'
    })
}

export function deleteLoginLogById(id) {
    return axios({
        url: '/loginLog',
        method: 'DELETE',
        params: {
            id
        }
    })
}