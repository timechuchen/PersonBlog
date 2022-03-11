import axios from '@/utils/request'

export function updateSite(hotTag) {
    return axios({
        url: '/updateSite',
        method: 'post',
        data: {
            hotTag,
        }
    })
}

export function getAllSite() {
    return axios({
        url: '/getAllSite',
        method: 'post'
    })
}