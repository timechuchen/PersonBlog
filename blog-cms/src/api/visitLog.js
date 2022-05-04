import axios from '@/utils/request'

export function getVisitLogList(queryInfo) {
    return axios({
        url: '/visitLogs',
        method: 'GET',
    })
}

export function deleteVisitLogById(id) {
    return axios({
        url: '/visitLog',
        method: 'DELETE',
        params: {
            id
        }
    })
}