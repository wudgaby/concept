package com.github.linyuzai.download.core.web.reactive;

import com.github.linyuzai.download.core.context.DownloadContext;
import com.github.linyuzai.download.core.exception.DownloadException;
import com.github.linyuzai.download.core.web.DownloadRequest;
import com.github.linyuzai.download.core.web.DownloadRequestProvider;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

/**
 * {@link ReactiveDownloadRequest} 的提供者。
 */
public class ReactiveDownloadRequestProvider implements DownloadRequestProvider {

    @Override
    public Mono<DownloadRequest> getRequest(DownloadContext context) {
        Object request = context.getOptions().getRequest();
        Object[] parameters = context.getOptions().getDownloadMethod().getParameters();
        return getServerHttpRequest(request, parameters).map(it -> {
            if (it == null) {
                throw new DownloadException("ServerHttpRequest not found");
            } else {
                return new ReactiveDownloadRequest(it);
            }
        });
    }

    /**
     * 如果下载参数中配置了请求对象则直接返回，
     * 判断方法参数中是否存在，有则返回该参数，
     * 否则使用 {@link ReactiveDownloadHolder} 获取。
     *
     * @param request    下载参数中的请求
     * @param parameters 方法入参
     * @return {@link ServerHttpRequest} 对应的 {@link Mono}
     */
    protected Mono<ServerHttpRequest> getServerHttpRequest(Object request, Object[] parameters) {
        if (request instanceof ServerHttpRequest) {
            return Mono.just((ServerHttpRequest) request);
        }
        for (Object parameter : parameters) {
            if (parameter instanceof ServerHttpRequest) {
                return Mono.just((ServerHttpRequest) parameter);
            }
        }
        return ReactiveDownloadHolder.getRequest();
    }
}
