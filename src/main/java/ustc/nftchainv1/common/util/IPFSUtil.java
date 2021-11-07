package ustc.nftchainv1.common.util;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.beans.factory.annotation.Autowired;
import ustc.nftchainv1.config.IPFSConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IPFSUtil {
    @Autowired
    private static IPFSConfig ipfsConfig;
    private static IPFS ipfs = ipfsConfig.IPFS();

    public static String upload(String fileName) throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(fileName));
        MerkleNode addResult = ipfs.add(file).get(0);
        return addResult.hash.toString();
    }

    public static String upload(byte[] data) throws IOException {
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(data);
        MerkleNode addResult = ipfs.add(file).get(0);
        return addResult.hash.toString();
    }

    public static byte[] download(String hash) {
        byte[] data = null;
        try {
            data = ipfs.cat(Multihash.fromBase58(hash));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void download(String hash, String destFile) {
        byte[] data = null;
        try {
            data = ipfs.cat(Multihash.fromBase58(hash));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data != null && data.length > 0) {
            File file = new File(destFile);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(data);
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
