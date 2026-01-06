// Quantity management for products
const quantities = {};

function increaseQty(productId) {
    const qtyElement = document.getElementById('qty-' + productId);
    let currentQty = parseInt(qtyElement.textContent);
    currentQty++;
    qtyElement.textContent = currentQty;
    quantities[productId] = currentQty;
}

function decreaseQty(productId) {
    const qtyElement = document.getElementById('qty-' + productId);
    let currentQty = parseInt(qtyElement.textContent);
    if (currentQty > 1) {
        currentQty--;
        qtyElement.textContent = currentQty;
        quantities[productId] = currentQty;
    }
}

function addToCart(productId) {
    const quantity = quantities[productId] || 1;
    const btn = event.target;
    btn.disabled = true;
    btn.textContent = 'Adding...';
    
    fetch('/user/cart/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'productId=' + productId + '&quantity=' + quantity
    })
    .then(response => response.text())
    .then(data => {
        if (data === 'success') {
            // Reset quantity display
            const qtyElement = document.getElementById('qty-' + productId);
            if (qtyElement) qtyElement.textContent = '1';
            quantities[productId] = 1;
            
            // Show success message without full reload
            btn.textContent = '✓ Added!';
            btn.style.background = 'linear-gradient(135deg, #2ecc71, #27ae60)';
            
            setTimeout(() => {
                location.reload();
            }, 500);
        } else {
            btn.disabled = false;
            btn.innerHTML = '<i class="fas fa-shopping-cart"></i> Add to Cart';
            alert('Failed to add product to cart.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        btn.disabled = false;
        btn.innerHTML = '<i class="fas fa-shopping-cart"></i> Add to Cart';
        alert('An error occurred.');
    });
}

function updateCartQty(cartItemId, newQty) {
    if (newQty < 1) {
        removeFromCart(cartItemId);
        return;
    }

    // Prevent multiple clicks
    const allButtons = document.querySelectorAll('.qty-btn');
    allButtons.forEach(btn => btn.disabled = true);

    fetch('/user/cart/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'cartItemId=' + cartItemId + '&quantity=' + newQty
    })
    .then(response => response.text())
    .then(data => {
        if (data === 'success' || data === 'removed') {
            setTimeout(() => {
                location.reload();
            }, 300);
        } else {
            allButtons.forEach(btn => btn.disabled = false);
            alert('Failed to update cart.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        allButtons.forEach(btn => btn.disabled = false);
        alert('An error occurred.');
    });
}

function removeFromCart(cartItemId) {
    if (confirm('Are you sure you want to remove this item?')) {
        // Disable all buttons
        const allButtons = document.querySelectorAll('button');
        allButtons.forEach(btn => btn.disabled = true);
        
        fetch('/user/cart/remove', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'cartItemId=' + cartItemId
        })
        .then(response => response.text())
        .then(data => {
            if (data === 'success') {
                setTimeout(() => {
                    location.reload();
                }, 300);
            } else {
                allButtons.forEach(btn => btn.disabled = false);
                alert('Failed to remove item.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            allButtons.forEach(btn => btn.disabled = false);
            alert('An error occurred.');
        });
    }
}
